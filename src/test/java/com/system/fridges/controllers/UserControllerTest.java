package com.system.fridges.controllers;

import com.system.fridges.models.entities.*;
import com.system.fridges.models.transferObjects.userObjects.UserFood;
import com.system.fridges.models.transferObjects.userObjects.UserOrder;
import com.system.fridges.models.transferObjects.userObjects.UserTransactionHistory;
import com.system.fridges.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void updateUserAccountTest() {
        MultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test file content".getBytes());
        String userEmail = "pasakane990@gmail.com";
        when(userService.findUserByEmail(userEmail)).thenReturn(new User());

        userController.updateUserAccount(userEmail, file);

        verify(userService, times(1)).saveUser(any(User.class), eq(file));
    }

    @Test
    public void getPresentUserReturnsUser() {
        User user = new User();
        String userEmail = "pasakane990@gmail.com";
        when(userService.findUserByEmail(userEmail)).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.getPresentUser(userEmail);

        verify(userService, times(1)).findUserByEmail(userEmail);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody(), user);
    }

    @Test
    public void getUserPhotoReturnsByte() {
        String userEmail = "pasakane990@gmail.com";
        byte[] expectedPhoto = new byte[]{};
        when(userService.getUserPhoto(userEmail)).thenReturn(expectedPhoto);

        ResponseEntity<byte[]> response = userController.getUserPhoto(userEmail);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(expectedPhoto, response.getBody());
        verify(userService, times(1)).getUserPhoto(userEmail);
    }

    @Test
    public void deleteUserTest() {
        String userEmail = "test@example.com";

        userController.deleteUser(userEmail);

        verify(userService, times(1)).deleteUser(userEmail);
    }


    @Test
    public void getAllOrdersReturnsOrders() {
        String userEmail = "test@example.com";
        List<UserOrder> expectedOrders = List.of();

        when(userService.getAllOrdersForUserByEmail(userEmail)).thenReturn(expectedOrders);

        ResponseEntity<List<UserOrder>> response = userController.getAllOrders(userEmail);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrders, response.getBody());
        verify(userService, times(1)).getAllOrdersForUserByEmail(userEmail);
    }

    @Test
    public void getHistoryUsingReturnsTransaction() {
        String userEmail = "test@example.com";
        List<UserTransactionHistory> expectedHistory = List.of();

        when(userService.getTransactionHistoryByEmail(userEmail)).thenReturn(expectedHistory);

        ResponseEntity<List<UserTransactionHistory>> response = userController.getHistoryUsing(userEmail);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedHistory, response.getBody());
        verify(userService, times(1)).getTransactionHistoryByEmail(userEmail);
    }

    @Test
    public void getUserFoodReturnsFood() {
        String userEmail = "test@example.com";
        List<UserFood> expectedUserFood = List.of();
        when(userService.getAllFoodUserByEmail(userEmail)).thenReturn(expectedUserFood);

        ResponseEntity<List<UserFood>> response = userController.getUserFood(userEmail);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUserFood, response.getBody());
        verify(userService, times(1)).getAllFoodUserByEmail(userEmail);
    }

    @Test
    public void getAvailableFridgesReturnsFridges() {
        String userEmail = "test@example.com";
        List<Fridge> expectedFridges = List.of();

        when(userService.getFridgesByUserEmail(userEmail)).thenReturn(expectedFridges);

        ResponseEntity<List<Fridge>> response = userController.getAvailableFridges(userEmail);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedFridges, response.getBody());
        verify(userService, times(1)).getFridgesByUserEmail(userEmail);
    }

    @Test
    public void hasActualSubscriptionReturnBoolean() {
        String userEmail = "test@example.com";
        boolean expectedHasSubscription = true;
        when(userService.hasActualSubscription(userEmail)).thenReturn(expectedHasSubscription);

        ResponseEntity<Boolean> response = userController.hasActualSubscription(userEmail);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedHasSubscription, response.getBody());
        verify(userService, times(1)).hasActualSubscription(userEmail);
    }

    @Test
    public void addTransactionTest() {
        Transaction testTransaction = new Transaction();

        userController.addTransaction(testTransaction);

        verify(userService, times(1)).addTransaction(testTransaction);
    }

    @Test
    void addOrdersReturnsValidResponse() {
        List<AutoOrder> orders = new ArrayList<>();

        userController.addOrders(orders);

        verify(userService, times(1)).addAutoOrders(orders);
    }

    @Test
    void addFoodReturnsValidResponse() {
        List<Food> food = new ArrayList<>();

        userController.addFood(food);

        verify(userService, times(1)).addFood(food);
    }

    @Test
    public void testAddSubscription() {
        Subscription testSubscription = new Subscription();

        userController.addSubscription(testSubscription);

        verify(userService, times(1)).addSubscription(testSubscription);
    }
}
