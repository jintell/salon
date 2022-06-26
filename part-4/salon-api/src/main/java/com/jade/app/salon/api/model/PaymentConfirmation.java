package com.jade.app.salon.api.model;

import com.jade.app.salon.api.config.SalonDetails;
import com.jade.app.salon.api.model.constant.TicketDto;
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
public class PaymentConfirmation implements Serializable {
    private SalonDetailsDto salonDetails;
    private TicketDto ticket;
}
