package com.system.fridges.controllers;


import com.system.fridges.models.entities.*;
import com.system.fridges.service.AdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DatabaseAdminControllerTest {

    @Mock
    private AdminServiceImpl adminService;

    @InjectMocks
    private DatabaseAdminController databaseAdminController;

    @Test
    void restoreDatabaseReturnsValidResponse() {
        String backupPath = "exampleBackupPathHash";
        byte[] backupPathHash = Base64.getEncoder().encode(backupPath.getBytes());
        Mockito.when(adminService.restoreDatabase(backupPathHash)).thenReturn(true);

        ResponseEntity<Boolean> responseEntity = databaseAdminController.restoreDatabase(backupPathHash);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());
    }

    @Test
    void backupDatabaseReturnsValidResponse() {
        String backupPath = "exampleBackupPathHash";
        byte[] backupPathHash = Base64.getEncoder().encode(backupPath.getBytes());
        Mockito.when(adminService.doBackupDatabase(backupPathHash)).thenReturn(true);

        ResponseEntity<Boolean> responseEntity = databaseAdminController.backupDatabase(backupPathHash);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());
    }

    @Test
    void addFridgesSuccessfulSave() {
        List<Fridge> fridges = new ArrayList<>();

        databaseAdminController.addFridges(fridges);

        Mockito.verify(adminService, times(1)).addFridges(fridges);
    }

    @Test
    void addUsersSuccessfulSave() {
        List<User> user = new ArrayList<>();

        databaseAdminController.addUsers(user);

        Mockito.verify(adminService, times(1)).addUser(user);
    }

    @Test
    void addSubscriptionSuccessfulSave() {
        List<Subscription> subscription = new ArrayList<>();

        databaseAdminController.addSubscriptions(subscription);

        Mockito.verify(adminService, times(1)).addSubscription(subscription);
    }

    @Test
    void addModelSuccessfulSave() {
        List<Model> models = new ArrayList<>();

        databaseAdminController.addModels(models);

        Mockito.verify(adminService, times(1)).addModel(models);
    }

    @Test
    void addOfficeSuccessfulSave() {
        List<Office> office = new ArrayList<>();

        databaseAdminController.addOffices(office);

        Mockito.verify(adminService, times(1)).addOffice(office);
    }

    @Test
    void addAccessSuccessfulSave() {
        List<Access> accesses = new ArrayList<>();

        databaseAdminController.addAccess(accesses);

        Mockito.verify(adminService, times(1)).addAccess(accesses);
    }

    @Test
    void addProductSuccessfulSave() {
        List<Product> products = new ArrayList<>();

        databaseAdminController.addProducts(products);

        Mockito.verify(adminService, times(1)).addProduct(products);
    }

    @Test
    void addOrdersSuccessfulSave() {
        List<AutoOrder> orders = new ArrayList<>();

        databaseAdminController.addAutoOrders(orders);

        Mockito.verify(adminService, times(1)).addAutoOrder(orders);
    }

    @Test
    void addTransactionSuccessfulSave() {
        List<Transaction> transactions = new ArrayList<>();

        databaseAdminController.addTransactions(transactions);

        Mockito.verify(adminService, times(1)).addTransaction(transactions);
    }

    @Test
    void addFoodSuccessfulSave() {
        List<Food> food = new ArrayList<>();

        databaseAdminController.addFood(food);

        Mockito.verify(adminService, times(1)).addFood(food);
    }

    @Test
    void deleteUserSuccessfulDelete() {
        List<Integer> userIds = new ArrayList<>();

        databaseAdminController.deleteUsers(userIds);

        Mockito.verify(adminService, times(1)).deleteUser(userIds);
    }

    @Test
    void deleteFridgesSuccessfulDelete() {
        List<Integer> fridgesIds = new ArrayList<>();

        databaseAdminController.deleteFridges(fridgesIds);

        Mockito.verify(adminService, times(1)).deleteFridge(fridgesIds);
    }

    @Test
    void deleteSubscriptionSuccessfulDelete() {
        List<Integer> subscriptionIds = new ArrayList<>();

        databaseAdminController.deleteSubscriptions(subscriptionIds);

        Mockito.verify(adminService, times(1)).deleteSubscription(subscriptionIds);
    }

    @Test
    void deleteModelSuccessfulDelete() {
        List<Integer> modelIds = new ArrayList<>();

        databaseAdminController.deleteModels(modelIds);

        Mockito.verify(adminService, times(1)).deleteModel(modelIds);
    }

    @Test
    void deleteOfficeSuccessfulDelete() {
        List<Integer> officeIds = new ArrayList<>();

        databaseAdminController.deleteOffices(officeIds);

        Mockito.verify(adminService, times(1)).deleteOffice(officeIds);
    }

    @Test
    void deleteAccessSuccessfulDelete() {
        List<Integer> accessIds = new ArrayList<>();

        databaseAdminController.deleteAccess(accessIds);

        Mockito.verify(adminService, times(1)).deleteAccess(accessIds);
    }

    @Test
    void deleteProductSuccessfulDelete() {
        List<Integer> productIds = new ArrayList<>();

        databaseAdminController.deleteProducts(productIds);

        Mockito.verify(adminService, times(1)).deleteProduct(productIds);
    }

    @Test
    void deleteOrderSuccessfulDelete() {
        List<Integer> orderIds = new ArrayList<>();

        databaseAdminController.deleteAutoOrders(orderIds);

        Mockito.verify(adminService, times(1)).deleteAutoOrder(orderIds);
    }

    @Test
    void deleteTransactionSuccessfulDelete() {
        List<Integer> transactionIds = new ArrayList<>();

        databaseAdminController.deleteTransactions(transactionIds);

        Mockito.verify(adminService, times(1)).deleteTransaction(transactionIds);
    }

    @Test
    void deleteFoodSuccessfulDelete() {
        List<Integer> foodIds = new ArrayList<>();

        databaseAdminController.deleteFood(foodIds);

        Mockito.verify(adminService, times(1)).deleteFood(foodIds);
    }


    @Test
    void getAllFridgesReturnsValidResponse() {
        when(adminService.getAllFridges()).thenReturn(new ArrayList<Fridge>());

        ResponseEntity<List<Fridge>> responseEntity = databaseAdminController.getAllFridges();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllFridges();
    }

    @Test
    void getAllUsersReturnsValidResponse() {
        when(adminService.getAllUsers()).thenReturn(new ArrayList<User>());

        ResponseEntity<List<User>> responseEntity = databaseAdminController.getAllUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllUsers();
    }

    @Test
    void getAllAccessReturnsValidResponse() {
        when(adminService.getAllAccess()).thenReturn(new ArrayList<Access>());

        ResponseEntity<List<Access>> responseEntity = databaseAdminController.getAllAccess();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllAccess();
    }

    @Test
    void getAllOfficeReturnsValidResponse() {
        when(adminService.getAllOffice()).thenReturn(new ArrayList<Office>());

        ResponseEntity<List<Office>> responseEntity = databaseAdminController.getAllOffices();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllOffice();
    }

    @Test
    void getAllModelReturnsValidResponse() {
        when(adminService.getAllModel()).thenReturn(new ArrayList<Model>());

        ResponseEntity<List<Model>> responseEntity = databaseAdminController.getAllModels();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllModel();
    }

    @Test
    void getAllSubscriptionReturnsValidResponse() {
        when(adminService.getAllSubscription()).thenReturn(new ArrayList<Subscription>());

        ResponseEntity<List<Subscription>> responseEntity = databaseAdminController.getAllSubscriptions();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllSubscription();
    }

    @Test
    void getAllOrderReturnsValidResponse() {
        when(adminService.getAllOrder()).thenReturn(new ArrayList<AutoOrder>());

        ResponseEntity<List<AutoOrder>> responseEntity = databaseAdminController.getAllOrders();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllOrder();
    }

    @Test
    void getAllProductReturnsValidResponse() {
        when(adminService.getAllProduct()).thenReturn(new ArrayList<Product>());

        ResponseEntity<List<Product>> responseEntity = databaseAdminController.getAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllProduct();
    }

    @Test
    void getAllFoodReturnsValidResponse() {
        when(adminService.getAllFood()).thenReturn(new ArrayList<Food>());

        ResponseEntity<List<Food>> responseEntity = databaseAdminController.getAllFood();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllFood();
    }

    @Test
    void getAllTransactionReturnsValidResponse() {
        when(adminService.getAllTransaction()).thenReturn(new ArrayList<Transaction>());

        ResponseEntity<List<Transaction>> responseEntity = databaseAdminController.getAllTransactions();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(adminService, times(1)).getAllTransaction();
    }
}

