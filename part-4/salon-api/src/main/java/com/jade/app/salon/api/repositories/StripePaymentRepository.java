package com.jade.app.salon.api.repositories;

import com.jade.app.salon.api.domain.StripePayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/17/22
 */
public interface StripePaymentRepository extends JpaRepository<StripePayment, Long> {
    Optional<StripePayment> findById(long id);

    Optional<StripePayment> findByIntendId(String intentId);
}
