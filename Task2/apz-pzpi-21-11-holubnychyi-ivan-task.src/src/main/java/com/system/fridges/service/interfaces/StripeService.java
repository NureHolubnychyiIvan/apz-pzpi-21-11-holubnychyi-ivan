package com.system.fridges.service.interfaces;

import com.stripe.exception.StripeException;
import com.system.fridges.models.transferObjects.stripeObjects.StripeRequest;
import com.system.fridges.models.transferObjects.stripeObjects.StripeResponse;

public interface StripeService {
    StripeResponse createPaymentIntent(StripeRequest request)
            throws StripeException;
}
