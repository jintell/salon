package com.jade.app.salon.api.model.constant;

import com.jade.app.salon.api.model.StripePaymentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/20/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto implements Serializable {
    private Long id;
    private StripePaymentResponse payment;
    private String ticketStatus;
}
