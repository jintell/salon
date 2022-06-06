package com.jade.app.salon.api.resource;

import com.jade.app.salon.api.services.SalonDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/4/22
 */
@Slf4j
@RequestMapping("/api/services")
@Api(value = "Salon Service", tags = { "Salon Services API" })
@RestController
@RequiredArgsConstructor
public class SalonServiceResource {

    private final SalonDetailService salonDetailService;

    @ApiOperation(value = "RetrieveAvailableSalonServicesAPI")
    @GetMapping("/retrieveAvailableSalonServices")
    public ResponseEntity<?> getSalonServices() {
        log.info("Request to get available salon services");
        return ResponseEntity.ok(salonDetailService.getSalonServiceDetails());
    }
}
