package com.dev.apptite.domain.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetodoDePagamento {

    private String stripeTestToken; // exemplo: "tok_visa"
    private String name;
    private String cardNumber;
    private String expirationDate; // formato MM/YY
    private String securityCode;
}