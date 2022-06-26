package com.jade.app.salon.api.domain;

import com.jade.app.salon.api.model.constant.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/17/22
 */

@Entity
@Table(name="payment")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StripePayment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="selected_service_id", nullable=false)
    private SalonServiceDetail selectedSalonService;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="slot_id", nullable=false)
    private Slot selectedSlot;
    private PaymentStatus status;
    @Column(name = "created")
    private Instant createdTime;
    @Column(name = "updated")
    private Instant updatedTime;
    @Column(name = "intent_id")
    private String intendId;
    @Column(name = "client_secret")
    private String secretId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Builder.Default
    @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="payment")
    private List<Ticket> tickets = new ArrayList<>();

    @PrePersist
    public void beforeSave(){
        status = PaymentStatus.PROCESSING;
        createdTime = Instant.now();
        updatedTime = Instant.now();
    }
}
