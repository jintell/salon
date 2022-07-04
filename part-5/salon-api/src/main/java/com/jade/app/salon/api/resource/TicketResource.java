package com.jade.app.salon.api.resource;

import com.jade.app.salon.api.services.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 7/1/22
 */
@Slf4j
@RequestMapping("/api/tickets")
@Api(value = "Ticket Service", tags = { "Ticket Services API" })
@RestController
@RequiredArgsConstructor
public class TicketResource {

    private final TicketService ticketService;

    @ApiOperation(value = "VerifyTicketAPI")
    @GetMapping("/{ticketId}")
    public ResponseEntity<?> confirmPayment(@PathVariable Long ticketId) {
        log.info("Confirmed payment for {}", ticketId);
        return ResponseEntity.ok(ticketService.verifyTicket(ticketId));
    }
}
