package com.system.fridges.controllers.functional;

import com.system.fridges.models.entities.*;
import com.system.fridges.models.transferObjects.userObjects.UserFood;
import com.system.fridges.models.transferObjects.userObjects.UserOrder;
import com.system.fridges.models.transferObjects.userObjects.UserTransactionHistory;
import com.system.fridges.repositories.AccessRepository;
import com.system.fridges.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
     void updateUserAccountTest() {
         ResponseEntity<Void> response = restTemplate.postForEntity("/user/account/{email}/update",null, Void.class, "test@example.com");

         assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void getPresentUserReturnsForbidden() {
        ResponseEntity<User> response = restTemplate.getForEntity("/user/account/{email}", User.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void getUserPhotoReturnsForbidden() {
        ResponseEntity<byte[]> response = restTemplate.getForEntity("/user/photo/{email}", byte[].class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void deleteUserTest() {
        ResponseEntity<Void> response = restTemplate.postForEntity("/user/account/{email}/delete", null, Void.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void getAllOrdersReturnsForbidden() {
        ResponseEntity<UserOrder> response = restTemplate.getForEntity("/user/history/orders/{email}", null, UserOrder.class, "pasakane990@gmail.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void getHistoryUsing() {
        ResponseEntity<UserTransactionHistory> response = restTemplate.getForEntity("/user/historyUsing/{email}", null, UserTransactionHistory.class, "pasakane990@gmail.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void getUserFood() {
        ResponseEntity<UserFood> response = restTemplate.getForEntity("/user/food/{email}", null, UserFood.class, "pasakane990@gmail.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void getAvailableFridges() {
        ResponseEntity<Fridge> response = restTemplate.getForEntity("/user/fridges/{email}", null, Fridge.class, "pasakane990@gmail.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void hasActualSubscription() {
        ResponseEntity<Boolean> response = restTemplate.getForEntity("/user/subscription/{email}", null, Boolean.class, "pasakane990@gmail.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void addTransaction() {
        ResponseEntity<Void> response = restTemplate.postForEntity("/user/addTransaction", null, Void.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }


    @Test
    void addOrdersReturnsValidResponse() {
        List<AutoOrder> orders = Collections.singletonList(new AutoOrder(LocalDateTime.now(), 2, accessRepository.findById(1).get(), productRepository.findById(1).get()));

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/user/autoOrdering/addOrder", orders, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void addFoodReturnsValidResponse() {
        List<Food> food = Collections.singletonList(new Food(new Date(), 2, "Food", new Transaction()));

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/user/addFood", food, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void addSubscription() {
        ResponseEntity<Void> response = restTemplate.postForEntity("/user/addSubscription", null, Void.class, "test@example.com");

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}
