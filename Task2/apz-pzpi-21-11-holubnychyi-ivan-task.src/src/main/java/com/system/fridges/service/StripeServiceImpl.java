package com.system.fridges.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.system.fridges.models.transferObjects.stripeObjects.StripeRequest;
import com.system.fridges.models.transferObjects.stripeObjects.StripeResponse;
import com.system.fridges.service.interfaces.StripeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeServiceImpl implements StripeService {

    @Value("${stripe.api.publicKey}")
    private String publicKey;

    private final static long PRICE = 100L;

    void init() {
        Stripe.apiKey = publicKey;
    }

    @Override
    public StripeResponse createPaymentIntent(StripeRequest request) {
        try {
            Customer customer = new Customer();
            customer.setEmail(request.getEmail());

            PaymentIntentCreateParams params =
                    PaymentIntentCreateParams.builder()
                            .setAmount(request.getAmount() * PRICE)
                            .setCurrency("usd")
                            .setCustomer(customer.getId())
                            .setAutomaticPaymentMethods(
                                    PaymentIntentCreateParams
                                            .AutomaticPaymentMethods
                                            .builder()
                                            .setEnabled(true)
                                            .build()
                            )
                            .build();
            PaymentIntent intent =
                    PaymentIntent.create(params);

            return new StripeResponse(intent.getId(),
                    intent.getClientSecret());

        } catch (StripeException e) {
            throw new IllegalArgumentException("Something went wrong");
        }
    }
}
