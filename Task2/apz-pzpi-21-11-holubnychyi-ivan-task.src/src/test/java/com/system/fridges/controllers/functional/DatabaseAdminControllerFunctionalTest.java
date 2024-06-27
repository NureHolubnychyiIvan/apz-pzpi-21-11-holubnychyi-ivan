package com.system.fridges.controllers.functional;


import com.system.fridges.models.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DatabaseAdminControllerFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void restoreDatabaseReturnsForbidden() {
        String backupPathHash = "exampleBackupPathHash";

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/databaseAdmin/restore?backupPathHash={backupPathHash}", null, String.class, backupPathHash);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void backupDatabaseReturnsForbidden() {
        String backupPathHash = "exampleBackupPathHash";

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/databaseAdmin/backup?backupPathHash={backupPathHash}", null, String.class, backupPathHash);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void addFridgesReturnsForbidden() {
        List<Fridge> fridges = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/addFridges", fridges, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void addUserReturnsForbidden() {
        List<User> users = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/addUsers", users, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void addSubscriptionReturnsForbidden() {
        List<Subscription> subscriptions = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/addSubscription", subscriptions, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void addModelReturnsForbidden() {
        List<Model> models = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/addModel", models, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void addOfficeReturnsForbidden() {
        List<Office> offices = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/addOffice", offices, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void addAccessReturnsForbidden() {
        List<Access> accesses = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/addAccess", accesses, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void addProductReturnsForbidden() {
        List<Product> products = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/addProduct", products, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void addOrderReturnsForbidden() {
        List<AutoOrder> autoOrders = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/addAutoOrder", autoOrders, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void addTransactionReturnsForbidden() {
        List<Transaction> transactions = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/addTransaction", transactions, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void addFoodReturnsForbidden() {
        List<Food> food = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/addFood", food, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void deleteFridgesReturnsForbidden() {
        List<Fridge> fridges = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/deleteFridges", fridges, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void deleteUserReturnsForbidden() {
        List<User> users = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/deleteUsers", users, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void deleteSubscriptionReturnsForbidden() {
        List<Subscription> users = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/deleteSubscription", users, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void deleteModelReturnsForbidden() {
        List<Model> models = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/deleteModel", models, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void deleteOfficeReturnsForbidden() {
        List<Office> offices = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/deleteOffice", offices, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void deleteAccessReturnsForbidden() {
        List<Access> accesses = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/deleteAccess", accesses, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void deleteProductReturnsForbidden() {
        List<Product> products = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/deleteProduct", products, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void deleteOrderReturnsForbidden() {
        List<AutoOrder> autoOrders = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/deleteAutoOrder", autoOrders, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void deleteTransactionReturnsForbidden() {
        List<Transaction> transactions = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/deleteTransaction", transactions, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void deleteFoodReturnsForbidden() {
        List<Food> food = new ArrayList<>();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/databaseAdmin/deleteFood", food, Void.class);

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
