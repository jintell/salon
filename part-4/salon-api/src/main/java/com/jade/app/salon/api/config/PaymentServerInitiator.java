package com.jade.app.salon.api.config;

import com.google.gson.JsonSyntaxException;
import com.jade.app.salon.api.domain.StripePayment;
import com.jade.app.salon.api.exception.SalonException;
import com.jade.app.salon.api.model.constant.PaymentStatus;
import com.jade.app.salon.api.repositories.StripePaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static spark.Spark.port;
import static spark.Spark.post;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/19/22
 */
@Component
@RequiredArgsConstructor
public class PaymentServerInitiator implements CommandLineRunner {
    @Value("${stripe.api.payment.secret.key}")
    private String secretKey;

    @Value("${stripe.endpoint.secret}")
    private String endpointSecret;

    @Value("${payment.server.port}")
    private int serverPort;

    public static String STRIPE_SECRET;

    @Override
    public void run(String... args) throws Exception {
        port(serverPort);
        Stripe.apiKey = secretKey;;
        STRIPE_SECRET = secretKey;
    }

}
