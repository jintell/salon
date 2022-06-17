package com.jade.app.salon.api.services;

import com.jade.app.salon.api.domain.SalonServiceDetail;
import com.jade.app.salon.api.domain.Slot;
import com.jade.app.salon.api.domain.SlotAvailableService;
import com.jade.app.salon.api.exception.SalonException;
import com.jade.app.salon.api.model.SalonServiceDetailDto;
import com.jade.app.salon.api.model.SlotDto;
import com.jade.app.salon.api.repositories.SlotAvailableServicesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/7/22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SalonSlotService {

    private final SlotAvailableServicesRepository availableServicesRepository;

    public List<SlotDto> getAvailableSlots(Long serviceId, String date) {
        return  availableServicesRepository.findBySalonServiceDetailId_IdAndSlotId_SlotForBetween(serviceId,
                dateToInstants(date, 0),
                dateToInstants(date, 1))
                .stream()
                .map(this::mapSlots)
                .collect(Collectors.toList());
    }

    private Instant dateToInstants(String strDate, int dayPart) {
        LocalDate date;
        try {
            date = convertToLocalDate(strDate);
        }catch (Exception e) {
            throw new SalonException("Invalid date Format. Should be yyyy-nn-dd");
        }

        return (dayPart == 0)?
                date.atTime(0, 0, 0)
                        .toInstant(ZoneOffset.UTC):
                date.atTime(23, 59, 59)
                        .toInstant(ZoneOffset.UTC);
    }

    private LocalDate convertToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);

    }

    private SlotDto mapSlots(SlotAvailableService availableService) {
        Slot slot = availableService.getSlotId();
        SalonServiceDetail salonServiceDetail = availableService.getSalonServiceDetailId();
        return SlotDto.builder()
                .id(slot.getId())
                .slotFor(slot.getSlotFor())
                .confirmedAt(slot.getSlotFor())
                .lockedAt(slot.getSlotFor())
                .status(slot.getStatus() == 0? "Available": "Not Available")
                .stylistName(slot.getStylistName())
                .selectedService(SalonServiceDetailDto.builder()
                        .id(salonServiceDetail.getId())
                        .name(salonServiceDetail.getName())
                        .description(salonServiceDetail.getDescription())
                        .price(salonServiceDetail.getPrice())
                        .timeInMinutes(salonServiceDetail.getTimeInMinutes())
                        .build())
                .build();
    }

}
