package com.system.fridges.controllers;


import com.system.fridges.models.transferObjects.stripeObjects.StripeRequest;
import com.system.fridges.models.transferObjects.stripeObjects.StripeResponse;
import com.system.fridges.service.StripeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private StripeServiceImpl stripeServiceImpl;

    @Test
    void createPaymentIntent() throws Exception {
        StripeRequest stripeRequest = new StripeRequest();
        stripeRequest.setEmail("pasakane990@gmail.com");
        when(stripeServiceImpl.createPaymentIntent(stripeRequest)).thenReturn(new StripeResponse());

        ResponseEntity<StripeResponse> responseEntity = paymentController.createPaymentIntent(stripeRequest);

        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
