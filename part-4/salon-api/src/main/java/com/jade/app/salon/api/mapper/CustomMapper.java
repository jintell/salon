package com.jade.app.salon.api.mapper;

import com.jade.app.salon.api.config.SalonDetails;
import com.jade.app.salon.api.domain.*;
import com.jade.app.salon.api.model.*;
import com.jade.app.salon.api.model.constant.TicketDto;
import com.jade.app.salon.api.model.constant.TicketStatus;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/20/22
 */
public class CustomMapper {

    public static StripePayment mapPayment(PaymentRequest request, SlotAvailableService availableService) {
        return StripePayment.builder()
                .amount(availableService.getSalonServiceDetailId().getPrice())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .selectedSalonService(availableService.getSalonServiceDetailId())
                .selectedSlot(availableService.getSlotId())
                .build();
    }

    public static StripePaymentResponse mapToPaymentResponse(StripePayment payment) {
        Slot slot = payment.getSelectedSlot();
        slot.setSalonServiceDetail(payment.getSelectedSalonService());
        return StripePaymentResponse.builder()
                .id(payment.getId())
                .asCents(0L)
                .createdTime(payment.getCreatedTime())
                .updatedTime(payment.getUpdatedTime())
                .amount(payment.getAmount())
                .email(payment.getEmail())
                .firstName(payment.getFirstName())
                .lastName(payment.getLastName())
                .intendId(payment.getIntendId())
                .clientSecret("***************************")
                .phoneNumber(payment.getPhoneNumber())
                .status(payment.getStatus())
                .selectedSalonService(mapToSalonServiceDetailDto(payment.getSelectedSalonService()))
                .selectedSlot(mapToSlotDto(slot))
                .build();
    }

    public static SlotDto mapToSlotDto(Slot slot) {
        return SlotDto.builder()
                .id(slot.getId())
                .slotFor(slot.getSlotFor())
                .confirmedAt(slot.getSlotFor())
                .lockedAt(slot.getSlotFor())
                .status(slot.getStatus() == 0? "Available": "Not Available")
                .stylistName(slot.getStylistName())
                .selectedService(SalonServiceDetailDto.builder()
                        .id(slot.getSalonServiceDetail().getId())
                        .name(slot.getSalonServiceDetail().getName())
                        .description(slot.getSalonServiceDetail().getDescription())
                        .price(slot.getSalonServiceDetail().getPrice())
                        .timeInMinutes(slot.getSalonServiceDetail().getTimeInMinutes())
                        .build())
                .build();
    }

    public static SalonServiceDetailDto mapToSalonServiceDetailDto(SalonServiceDetail detail) {
        return SalonServiceDetailDto.builder()
                        .id(detail.getId())
                        .name(detail.getName())
                        .description(detail.getDescription())
                        .price(detail.getPrice())
                        .timeInMinutes(detail.getTimeInMinutes())
                        .build();

    }

    public static PaymentConfirmation mapToPaymentConfirmation(Ticket ticket, SalonDetails salonDetails){
        return PaymentConfirmation.builder()
                .salonDetails(mapToSalonDetailDto(salonDetails))
                .ticket(mapToTicketDto(ticket))
                .build();
    }

    public static TicketDto mapToTicketDto(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId())
                .payment(mapToPaymentResponse(ticket.getPayment()))
                .ticketStatus((ticket.getTicketStatus() == TicketStatus.BOOKED? "BOOKED" : "NOT BOOKED"))
                .build();
    }

    public static SalonDetailsDto mapToSalonDetailDto(SalonDetails salonDetails) {
        return SalonDetailsDto.builder()
                .name(salonDetails.getName())
                .address(salonDetails.getAddress())
                .city(salonDetails.getCity())
                .phone(salonDetails.getPhone())
                .state(salonDetails.getState())
                .zipcode(salonDetails.getZipcode())
                .build();
    }





}
