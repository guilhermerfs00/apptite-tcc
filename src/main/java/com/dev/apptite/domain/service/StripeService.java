package com.dev.apptite.domain.service;

import com.dev.apptite.domain.entity.MetodoDePagamento;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StripeService {

    @Value("${stripe.token}")
    private String stripeToken;

    @PostConstruct
    public void init() {
        Stripe.apiKey = this.stripeToken;
    }

    public com.stripe.model.PaymentMethod createCardPaymentMethod(MetodoDePagamento dto) throws StripeException {
        Map<String, Object> card = new HashMap<>();
        card.put("number", dto.getCardNumber());
        card.put("exp_month", dto.getExpirationDate().split("/")[0]);
        card.put("exp_year", "20" + dto.getExpirationDate().split("/")[1]);
        card.put("cvc", dto.getSecurityCode());

        Map<String, Object> params = new HashMap<>();
        params.put("type", "card");
        params.put("card", Map.of("token", "tok_visa")); // ← importante


        return com.stripe.model.PaymentMethod.create(params);
    }


    public PaymentIntent createPaymentIntent(Map<String, Object> params) throws StripeException {
        return PaymentIntent.create(params);
    }
}