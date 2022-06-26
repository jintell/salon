package com.jade.app.salon.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/19/22
 */
@Data
@Builder
public class PaymentRequest implements Serializable {
    private Long slotId;
    private Long salonServiceDetailId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
