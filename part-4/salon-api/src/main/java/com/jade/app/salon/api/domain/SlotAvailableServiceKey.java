package com.jade.app.salon.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/8/22
 */
@Data
@EqualsAndHashCode
@Embeddable
public class SlotAvailableServiceKey implements Serializable {
    @Column(name = "slot_id")
    Long slotId;

    @Column(name = "available_services_id")
    Long availableServiceId;
}
