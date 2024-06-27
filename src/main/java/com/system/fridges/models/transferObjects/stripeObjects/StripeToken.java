package com.system.fridges.models.transferObjects.stripeObjects;

import lombok.Data;

@Data
public class StripeToken {

    private String cardNumber;

    private String expMonth;

    private String expYear;

    private String cvv;

    private String token;

    private String userName;

    private boolean success;

}
