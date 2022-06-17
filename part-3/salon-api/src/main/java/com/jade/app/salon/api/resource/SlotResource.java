package com.jade.app.salon.api.resource;

import com.jade.app.salon.api.services.SalonSlotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * @Date: 6/7/22
 */
@Slf4j
@RequestMapping("/api/services")
@Api(value = "Salon Service", tags = { "Salon Services API" })
@RestController
@RequiredArgsConstructor
public class SlotResource {
    private final SalonSlotService salonSlotService;

    @ApiOperation(value = "RetrieveAvailableSlotsAPI")
    @GetMapping("/retrieveAvailableSlots/{salonServiceId}/{formattedDate}")
    public ResponseEntity<?> getAvailableSlots(@PathVariable Long salonServiceId,
                                               @ApiParam("Format YYY-MM-DD") @PathVariable String formattedDate){
        log.info("Request to get available salon services by Id and Date");
        return ResponseEntity.ok(salonSlotService.getAvailableSlots(salonServiceId, formattedDate));
    }
}
