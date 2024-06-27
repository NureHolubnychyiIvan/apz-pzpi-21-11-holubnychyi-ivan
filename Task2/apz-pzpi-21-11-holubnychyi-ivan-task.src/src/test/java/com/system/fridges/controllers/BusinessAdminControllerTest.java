package com.system.fridges.controllers;

import com.system.fridges.models.entities.*;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeSpending;
import com.system.fridges.service.AdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusinessAdminControllerTest {

    @InjectMocks
    private BusinessAdminController businessAdminController;

    @Mock
    private AdminServiceImpl adminService;

    @Test
    void getSpendingElectricityReturnsValidResponse() {
        float price = 10.0f;
        String nameCompany = "ExampleCompany";
        List<FridgeSpending> expectedSpending = List.of(new FridgeSpending(1, "Fg", 345.32, 423.4));
        when(adminService.getSpendingElectricity(price, nameCompany)).thenReturn(expectedSpending);

        ResponseEntity<List<FridgeSpending>> responseEntity = businessAdminController.getSpendingElectricityByCompany(price, nameCompany);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedSpending, responseEntity.getBody());
        verify(adminService, times(1)).getSpendingElectricity(price, nameCompany);
    }

    @Test
    void getSumSpendingReturnsValidResponse() {
        float price = 10.0f;
        String nameCompany = "ExampleCompany";
        float expectedSum = 50.0f;
        when(adminService.getSumSpending(price, nameCompany)).thenReturn(expectedSum);

        ResponseEntity<Float> responseEntity = businessAdminController.getSumSpendingByCompany(price, nameCompany);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedSum, responseEntity.getBody());
        verify(adminService, times(1)).getSumSpending(price, nameCompany);
    }

    @Test
    void getAllFridgesReturnsValidResponse() {
        when(adminService.getAllFridges()).thenReturn(new ArrayList<Fridge>());

        ResponseEntity<List<Fridge>> responseEntity = businessAdminController.getAllFridges();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllFridges();
    }

    @Test
    void getAllUsersReturnsValidResponse() {
        when(adminService.getAllUsers()).thenReturn(new ArrayList<User>());

        ResponseEntity<List<User>> responseEntity = businessAdminController.getAllUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllUsers();
    }

    @Test
    void getAllAccessReturnsValidResponse() {
        when(adminService.getAllAccess()).thenReturn(new ArrayList<Access>());

        ResponseEntity<List<Access>> responseEntity = businessAdminController.getAllAccess();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllAccess();
    }

    @Test
    void getAllOfficeReturnsValidResponse() {
        when(adminService.getAllOffice()).thenReturn(new ArrayList<Office>());

        ResponseEntity<List<Office>> responseEntity = businessAdminController.getAllOffices();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllOffice();
    }

    @Test
    void getAllModelReturnsValidResponse() {
        when(adminService.getAllModel()).thenReturn(new ArrayList<Model>());

        ResponseEntity<List<Model>> responseEntity = businessAdminController.getAllModels();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllModel();
    }

    @Test
    void getAllSubscriptionReturnsValidResponse() {
        when(adminService.getAllSubscription()).thenReturn(new ArrayList<Subscription>());

        ResponseEntity<List<Subscription>> responseEntity = businessAdminController.getAllSubscriptions();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllSubscription();
    }

    @Test
    void getAllOrderReturnsValidResponse() {
        when(adminService.getAllOrder()).thenReturn(new ArrayList<AutoOrder>());

        ResponseEntity<List<AutoOrder>> responseEntity = businessAdminController.getAllOrders();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllOrder();
    }

    @Test
    void getAllProductReturnsValidResponse() {
        when(adminService.getAllProduct()).thenReturn(new ArrayList<Product>());

        ResponseEntity<List<Product>> responseEntity = businessAdminController.getAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllProduct();
    }

    @Test
    void getAllFoodReturnsValidResponse() {
        when(adminService.getAllFood()).thenReturn(new ArrayList<Food>());

        ResponseEntity<List<Food>> responseEntity = businessAdminController.getAllFood();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllFood();
    }

    @Test
    void getAllTransactionReturnsValidResponse() {
        when(adminService.getAllTransaction()).thenReturn(new ArrayList<Transaction>());

        ResponseEntity<List<Transaction>> responseEntity = businessAdminController.getAllTransactions();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllTransaction();
    }
}

