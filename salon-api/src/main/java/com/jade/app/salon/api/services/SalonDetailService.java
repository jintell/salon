package com.jade.app.salon.api.services;

import com.jade.app.salon.api.domain.SalonServiceDetail;
import com.jade.app.salon.api.repositories.SalonServiceDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/4/22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SalonDetailService {

    private final SalonServiceDetailRepository salonServiceDetailRepository;

    public List<SalonServiceDetail> getSalonServiceDetails() {
        return salonServiceDetailRepository.findAll();
    }

}
