package com.system.fridges.controllers.functional;

import com.system.fridges.models.transferObjects.fridgeObjects.FridgeSpending;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusinessAdminControllerFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getSpendingElectricityReturnsForbidden() {
        float price = 10.0f;
        String nameCompany = "Epam";

        ResponseEntity<FridgeSpending> responseEntity = restTemplate.getForEntity("/businessAdmin/electricity?price={price}&nameCompany={nameCompany}", FridgeSpending.class, price, nameCompany);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }


    @Test
    void getSumSpendingReturnsForbidden() {
        float price = 10.0f;
        String nameCompany = "Epam";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/electricitySum?price={price}&nameCompany={nameCompany}",
                String.class, price, nameCompany);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllFridgesReturnsForbidden() {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allFridges",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllUsersReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allUsers",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllAccessReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allAccess",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllOfficeReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allOffice",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllModelReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allModel",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllSubscriptionReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allSubscription",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllOrderReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allAutoOrder",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllProductReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allProduct",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllFoodReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allFood",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void getAllTransactionReturnsForbidden() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "/businessAdmin/allTransaction",
                String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

}
