package com.jade.app.salon.api.services;

import com.jade.app.salon.api.domain.Ticket;
import com.jade.app.salon.api.exception.SalonException;
import com.jade.app.salon.api.mapper.CustomMapper;
import com.jade.app.salon.api.model.constant.TicketDto;
import com.jade.app.salon.api.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 7/1/22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketDto verifyTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(()-> new SalonException("Ticket Id is invalid"));
        return CustomMapper.mapToTicketDto(ticket);
    }

}
