package com.jade.app.salon.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/4/22
 */
@Entity
@Table(name="slot")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slot implements Serializable {
    @Id
    private Long id;
    @Column(name = "confirmed_at")
    private Instant confirmedAt;
    @Column(name = "locked_at")
    private Instant lockedAt;
    @Column(name = "slot_for")
    private Instant slotFor;
    private Integer status;
    @Column(name = "stylist_name")
    private String stylistName;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="selected_service_id", unique=true, nullable=false)
    private SalonServiceDetail salonServiceDetail;

}
