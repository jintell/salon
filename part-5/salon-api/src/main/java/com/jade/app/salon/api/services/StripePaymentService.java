package com.jade.app.salon.api.services;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jade.app.salon.api.config.PaymentServerInitiator;
import com.jade.app.salon.api.config.SalonDetails;
import com.jade.app.salon.api.domain.Slot;
import com.jade.app.salon.api.domain.SlotAvailableService;
import com.jade.app.salon.api.domain.StripePayment;
import com.jade.app.salon.api.domain.Ticket;
import com.jade.app.salon.api.exception.SalonException;
import com.jade.app.salon.api.mapper.CustomMapper;
import com.jade.app.salon.api.model.PaymentConfirmation;
import com.jade.app.salon.api.model.PaymentId;
import com.jade.app.salon.api.model.PaymentRequest;
import com.jade.app.salon.api.model.StripePaymentResponse;
import com.jade.app.salon.api.model.constant.PaymentStatus;
import com.jade.app.salon.api.model.constant.TicketStatus;
import com.jade.app.salon.api.repositories.SlotRepository;
import com.jade.app.salon.api.repositories.StripePaymentRepository;
import com.jade.app.salon.api.repositories.TicketRepository;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.net.RequestOptions;
import com.stripe.net.Webhook;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.jade.app.salon.api.config.PaymentServerInitiator.STRIPE_SECRET;
import static spark.Spark.post;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/17/22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StripePaymentService {

    @Value(("${payment.server.url}"))
    private String paymentServerUrl;

    private final SalonPaymentValidator paymentValidator;
    private final StripePaymentRepository paymentRepository;
    private final SlotRepository slotRepository;
    private final TicketRepository ticketRepository;
    private final SalonDetails salonDetails;

    public StripePaymentResponse makePayment(PaymentRequest request) {
        SlotAvailableService availableService
                = paymentValidator.validateAvailability(request.getSlotId(), request.getSalonServiceDetailId());
        PaymentIntent paymentIntent = createPaymentIntent(availableService);
        // update payment request
        request.setClientSecret(paymentIntent.getClientSecret());
        request.setIntentId(paymentIntent.getId());
        //Create Payment Record in DB
        StripePayment payment = paymentRepository.save(CustomMapper.mapPayment(request, availableService));
        // Update Slot to locked -1
        Slot selectedSlot = availableService.getSlotId();
        selectedSlot.setSalonServiceDetail(payment.getSelectedSalonService());
        updateSlot(selectedSlot, 1);
        return CustomMapper.mapToPaymentResponse(payment);
    }

    public PaymentConfirmation confirmPayment(Long paymentId) {
        return CustomMapper.mapToPaymentConfirmation(
                verifyPayment(paymentId), salonDetails
        );
    }

    private PaymentIntent createPaymentIntent(SlotAvailableService availableService) {
        if(availableService.getSalonServiceDetailId().getPrice() < 50) throw new SalonException("Amount too low");
        Map<String,Object> additionalParams = new HashMap<>();
        // Configure stripe endpoint
        PaymentIntentCreateParams params = PaymentIntentCreateParams
                .builder()
                .setAmount(availableService.getSalonServiceDetailId().getPrice())
                .setCurrency("usd")
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods
                                .builder()
                                .setEnabled(true)
                                .build()
                )
                .build();

        try {
            return PaymentIntent.create(params);
        } catch (StripeException e) {
            throw new SalonException("Payment Intent could not be created "+ e.getMessage());
        }

    }

    private Ticket verifyPayment(Long id) throws SalonException {
        Ticket ticket;
        // Configure stripe
        RequestOptions requestOptions = RequestOptions.builder()
                .setApiKey(STRIPE_SECRET)
                .build();
        try {
            StripePayment payment = paymentRepository.findById(id).orElseThrow(()-> new SalonException("Payment Id not Found"));
            PaymentIntent paymentIntent = PaymentIntent.retrieve(payment.getIntendId(), requestOptions);
            if("succeeded".equalsIgnoreCase(paymentIntent.getStatus())){
                payment.setStatus(PaymentStatus.SUCCESS);
                updatePaymentStatus(payment);
                Slot slot = payment.getSelectedSlot();
                slot.setSalonServiceDetail(payment.getSelectedSalonService());
                updateSlot(slot, 2);
                ticket = generateTicket(payment, TicketStatus.BOOKED);
            }else {
                payment.setStatus(PaymentStatus.FAILED);
                ticket = generateTicket(payment, TicketStatus.UNBOOKED);
                throw new SalonException(CustomMapper.mapToPaymentConfirmation(ticket, salonDetails));
            }
            paymentRepository.save(payment);
        } catch (StripeException e) {
            throw new SalonException(id+" Payment verification error");
        }

        return ticket;
    }

    private Ticket generateTicket(StripePayment payment, TicketStatus status) throws SalonException {
        Ticket newTicket = Ticket.builder()
                .payment(payment)
                .ticketStatus(status)
                .build();
        try {
            return ticketRepository.save(newTicket);
        }catch (Exception e) { throw new SalonException("Could not create ticket"); }
    }

    private StripePayment updatePaymentIntent(Long id, String intentId) {
        if(id == null) return null;
        StripePayment payment = paymentRepository.findById(id)
                .orElseThrow(()-> new SalonException("Could not get payment Id with: "+id));
        if(payment != null) payment.setIntendId(intentId);
        return paymentRepository.save(payment);
    }

    private void updatePaymentStatus(StripePayment payment) {
         paymentRepository.save(payment);
    }

    private void updateSlot(Slot slot, int status) {
        if(status == 1)
            slot.setLockedAt(Instant.now());
        else if(status == 2)
            slot.setConfirmedAt(Instant.now());
        slot.setStatus(status);
        slotRepository.save(slot);
    }


}
