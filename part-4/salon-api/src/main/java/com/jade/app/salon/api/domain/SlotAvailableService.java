package com.jade.app.salon.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/8/22
 */
@Entity
@Table(name="slot_available_services")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotAvailableService implements Serializable {

    @EmbeddedId
    SlotAvailableServiceKey id;

    @ManyToOne
    @MapsId("slotId")
    @JoinColumn(name = "slot_id")
    Slot slotId;

    @ManyToOne
    @MapsId("availableServiceId")
    @JoinColumn(name = "available_services_id")
    SalonServiceDetail salonServiceDetailId;





}
