package com.system.fridges.controllers.functional;



import com.system.fridges.models.transferObjects.foodObjects.FoodInFridge;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeOrder;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeTransactionHistory;
import com.system.fridges.repositories.AccessRepository;
import com.system.fridges.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FridgeControllerFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void getAllFoodReturnsValidResponse() {
        int fridgeId = 1;

        ResponseEntity<List<FoodInFridge>> responseEntity = restTemplate.exchange(
                "/fridge/foodInside?fridgeId={fridgeId}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FoodInFridge>>() {},
                fridgeId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getAutoOrderingReturnsValidResponse() {
        int fridgeId = 1;

        ResponseEntity<FridgeOrder[]> responseEntity = restTemplate.getForEntity("/fridge/autoOrdering/{email}?fridgeId={fridgeId}",
                FridgeOrder[].class, "example@example.com", fridgeId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getTransactionHistoriesReturnsValidResponse() {
        int fridgeId = 1;

        ResponseEntity<FridgeTransactionHistory[]> responseEntity = restTemplate.getForEntity("/fridge/transactions?fridgeId={fridgeId}",
                FridgeTransactionHistory[].class, fridgeId);
        System.out.println(Arrays.toString(responseEntity.getBody()));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void doInventorySendMessages() {
        int fridgeId = 1;

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/fridge/inventory", fridgeId, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void doAutoOrderReturnsValidResponse() {
        int fridgeId = 1;

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/fridge/doAutoOrder", fridgeId, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}

