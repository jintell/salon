package com.jade.app.salon.api.repositories;

import com.jade.app.salon.api.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/20/22
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
