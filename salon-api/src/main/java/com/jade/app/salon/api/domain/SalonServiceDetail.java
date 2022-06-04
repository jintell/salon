package com.jade.app.salon.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/4/22
 */
@Entity
@Table(name="salon_service_detail")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalonServiceDetail implements Serializable {

    @Id
    private Long id;
    private String description;
    private String name;
    private Long price;
    @Column(name = "time_in_minutes")
    private Integer timeInMinutes;
    @Builder.Default
    @OneToMany(fetch= FetchType.LAZY, mappedBy="salonServiceDetail")
    private List<Slot> slot = new ArrayList<>();


}
