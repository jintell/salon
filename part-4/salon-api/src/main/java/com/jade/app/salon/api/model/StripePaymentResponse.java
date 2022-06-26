package com.jade.app.salon.api.model;

import com.jade.app.salon.api.model.constant.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/17/22
 */
@Data
@Builder
public class StripePaymentResponse implements Serializable {
    private Long id;
    private Long amount;
    private Long asCents;
    private SalonServiceDetailDto selectedSalonService;
    private SlotDto selectedSlot;
    private PaymentStatus status;
    private Instant createdTime;
    private Instant updatedTime;
    private String intendId;
    private String clientSecret;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
