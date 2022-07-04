package com.jade.app.salon.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/26/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalonDetailsDto implements Serializable {
    private String name;
    private String city;
    private String address;
    private String phone;
    private String state;
    private String zipcode;
}
