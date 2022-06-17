package com.jade.app.salon.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/8/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SlotDto implements Serializable {
    private Long id;
    private Instant slotFor;
    private Instant confirmedAt;
    private Instant lockedAt;
    private String stylistName;
    private String status;
    private SalonServiceDetailDto selectedService;

}
