package com.jade.app.salon.api.repositories;

import com.jade.app.salon.api.domain.SalonServiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/4/22
 */
public interface SalonServiceDetailRepository extends JpaRepository<SalonServiceDetail, Long> {
}
