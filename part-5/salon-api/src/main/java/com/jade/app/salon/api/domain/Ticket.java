package com.jade.app.salon.api.domain;

import com.jade.app.salon.api.model.constant.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/20/22
 */
@Entity
@Table(name="ticket")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name="ticket_status", nullable=false)
    private TicketStatus ticketStatus;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="payment_id", nullable=false)
    private StripePayment payment;

}
