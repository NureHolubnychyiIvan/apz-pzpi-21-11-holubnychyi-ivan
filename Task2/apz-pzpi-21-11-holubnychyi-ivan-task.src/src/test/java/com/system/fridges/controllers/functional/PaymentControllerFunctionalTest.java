package com.system.fridges.controllers.functional;

import com.system.fridges.models.transferObjects.stripeObjects.StripeRequest;
import com.system.fridges.models.transferObjects.stripeObjects.StripeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentControllerFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreatePaymentToken() {
        StripeRequest stripeRequest = new StripeRequest();
        stripeRequest.setEmail("pasakane990@gmail.com");
        stripeRequest.setAmount(400L);

        ResponseEntity<StripeResponse> responseEntity = restTemplate.postForEntity(
                "/subscription/buy/card", stripeRequest, StripeResponse.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
