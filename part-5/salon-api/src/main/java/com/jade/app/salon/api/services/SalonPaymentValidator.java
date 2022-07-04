package com.jade.app.salon.api.services;

import com.jade.app.salon.api.domain.SlotAvailableService;
import com.jade.app.salon.api.exception.SalonException;
import com.jade.app.salon.api.repositories.SlotAvailableServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/19/22
 */
@Service
@RequiredArgsConstructor
public class SalonPaymentValidator {
    private final SlotAvailableServicesRepository servicesRepository;

    private SlotAvailableService validateSelectedSalonService(Long slotId, Long selectedServiceId) throws SalonException {
        return servicesRepository.findBySalonServiceDetailId_IdAndSlotId_Id(selectedServiceId, slotId)
                .orElseThrow(()-> new SalonException("No Slot Available for selected service"));
    }

    public SlotAvailableService validateAvailability(Long slotId, Long selectedServiceId) throws SalonException {
        SlotAvailableService availableService = validateSelectedSalonService(slotId, selectedServiceId);
        if(availableService.getSlotId().getStatus() != 0) throw new SalonException("The selected Slot is not Available");
        return availableService;
    }
}
