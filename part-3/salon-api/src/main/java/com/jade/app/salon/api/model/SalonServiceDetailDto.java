package com.jade.app.salon.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/8/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalonServiceDetailDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Integer timeInMinutes;

}
