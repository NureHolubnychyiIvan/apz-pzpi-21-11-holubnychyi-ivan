package com.system.fridges.controllers;

import com.stripe.exception.StripeException;
import com.system.fridges.models.transferObjects.stripeObjects.StripeRequest;
import com.system.fridges.models.transferObjects.stripeObjects.StripeResponse;
import com.system.fridges.service.StripeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscription")
public class PaymentController {

    @Autowired
    private StripeServiceImpl stripeServiceImpl;

    @PostMapping("/buy/card")
    public ResponseEntity<StripeResponse> createPaymentIntent(@RequestBody StripeRequest request) {
        return ResponseEntity.ok(stripeServiceImpl.createPaymentIntent(request));
    }
}
