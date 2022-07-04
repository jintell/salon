package com.jade.app.salon.api.resource;

import com.jade.app.salon.api.model.PaymentRequest;
import com.jade.app.salon.api.services.StripePaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/19/22
 */
@Slf4j
@RequestMapping("/api/payments")
@Api(value = "Payment Service", tags = { "Payment Services API" })
@RestController
@RequiredArgsConstructor
public class PaymentResource {

    private final StripePaymentService stripePaymentService;

    @ApiOperation(value = "InitiatePaymentAPI")
    @PostMapping("/initiate")
    public ResponseEntity<?> initiatePayment(@RequestBody PaymentRequest request) {
        log.info("initiate new payment for {} {}", request.getFirstName(), request.getLastName());
        return ResponseEntity.ok(stripePaymentService.makePayment(request));
    }

    @ApiOperation(value = "VerifyPaymentAndConfirmSlotAPI")
    @PutMapping("/confirm/{id}")
    public ResponseEntity<?> confirmPayment(@PathVariable Long id) {
        log.info("Confirmed payment for {}", id);
        return ResponseEntity.ok(stripePaymentService.confirmPayment(id));
    }

}
