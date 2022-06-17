package com.jade.app.salon.api.repositories;

import com.jade.app.salon.api.domain.SlotAvailableService;
import com.jade.app.salon.api.domain.SlotAvailableServiceKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/8/22
 */
public interface SlotAvailableServicesRepository extends JpaRepository<SlotAvailableService, SlotAvailableServiceKey> {

    List<SlotAvailableService> findBySalonServiceDetailId_IdAndSlotId_SlotForBetween(Long serviceId, Instant from, Instant to);
}
