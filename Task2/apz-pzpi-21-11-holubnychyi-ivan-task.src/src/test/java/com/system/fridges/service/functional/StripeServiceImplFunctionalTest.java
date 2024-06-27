package com.system.fridges.service.functional;

import com.stripe.exception.StripeException;
import com.system.fridges.models.transferObjects.stripeObjects.StripeRequest;
import com.system.fridges.models.transferObjects.stripeObjects.StripeResponse;
import com.system.fridges.service.StripeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StripeServiceImplFunctionalTest {

    @Autowired
    private StripeServiceImpl stripeServiceImpl;

    @Test
    void testCreatePaymentIntent() throws StripeException {
        StripeRequest request = new StripeRequest();
        request.setEmail("test@example.com");
        request.setAmount(100L);

        StripeResponse response = stripeServiceImpl.createPaymentIntent(request);

        assertNotNull(response);
    }
}
