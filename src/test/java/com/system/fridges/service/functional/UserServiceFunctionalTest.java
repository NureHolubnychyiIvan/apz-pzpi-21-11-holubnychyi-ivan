package com.system.fridges.service.functional;

import com.system.fridges.models.entities.*;
import com.system.fridges.models.transferObjects.userObjects.UserFood;
import com.system.fridges.models.transferObjects.userObjects.UserOrder;
import com.system.fridges.models.transferObjects.userObjects.UserTransactionHistory;
import com.system.fridges.repositories.*;
import com.system.fridges.service.AuthenticationServiceImpl;
import com.system.fridges.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceFunctionalTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private FridgeRepository fridgeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AutoOrderRepository autoOrderRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Test
    @Transactional
    void getFridgesByUserEmailTest() {
        String email = "pasakane990@gmail.com";

        List<Fridge> result = userService.getFridgesByUserEmail(email);

        assertNotNull(result);
    }

    @Test
    void getAllFoodUserByEmailTest() {
        String userEmail = "pasakane990@gmail.com";

        List<UserFood> userFoods = userService.getAllFoodUserByEmail(userEmail);

        assertNotNull(userFoods);
    }

    @Test
    void getTransactionHistoryByEmailTest() {
        String userEmail = "test@example.com";

        List<UserTransactionHistory> transactionHistoryList = userService.getTransactionHistoryByEmail(userEmail);

        assertNotNull(transactionHistoryList);
    }

    @Test
    void getAllOrdersForUserByEmailTest() {
        String userEmail = "pasakane990@gmail.com";

        List<UserOrder> userOrderList = userService.getAllOrdersForUserByEmail(userEmail);

        assertNotNull(userOrderList);
    }

    @Test
    void getAllUsersTest() {
        List<User> userList = userService.getAllUsers();

        assertNotNull(userList);
    }

    @Test
    void getUserByIdTest() {
        Integer userId = 1;

        User resultUser = userService.getUserById(userId);

        assertNotNull(resultUser);
    }

    @Test
    void deleteUserTest() {
        String userEmail = "test@example.com";

        userService.deleteUser(userEmail);

        assertTrue(userRepository.findUserByEmail("test@example.com").isEmpty());
    }

    @Test
    void findUserByEmailTest() {
        String userEmail = "pasakane990@gmail.com";

        User resultUser = userService.findUserByEmail(userEmail);

        assertNotNull(resultUser);
    }

    @Test
    void hasActualSubscriptionTest() {
        String userEmail = "pasakane990@gmail.com";

        boolean hasSubscription = userService.hasActualSubscription(userEmail);

        assertTrue(hasSubscription);
    }

    @Test
    void addTransactionTest() {
        Transaction transaction = transactionRepository.findById(1).get();

        userService.addTransaction(transaction);

        assertNotNull(transactionRepository.findById(1).get());
    }

    @Test
    void addAutoOrdersTest() {
        List<AutoOrder> order = List.of(autoOrderRepository.findById(8).get());

        userService.addAutoOrders(order);

        assertNotNull(autoOrderRepository.findById(8).get());
    }

    @Test
    void addFoodTest() {
        List<Food> food = List.of(foodRepository.findById(1).get());

        userService.addFood(food);

        assertNotNull(foodRepository.findById(1).get());
    }

    @Test
    void addSubscriptionTest() {
        Subscription subscription = subscriptionRepository.findById(1).get();

        userService.addSubscription(subscription);

        assertNotNull(subscriptionRepository.findById(1).get());
    }

    @Test
    void saveUserTest() {
        User testUser = userRepository.findById(1).get();

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "test photo content".getBytes()
        );

        userService.saveUser(testUser, file);

        User savedUser = userRepository.findById(testUser.getUserId()).orElse(null);
        assertNotNull(savedUser);
        assertNotNull(savedUser.getPhoto());


        File savedPhoto = new File(savedUser.getPhoto());
        assertTrue(savedPhoto.exists());
    }

    @Test
    void testGetUserPhoto() {
        User testUser = userRepository.findById(1).get();
        testUser.setPhoto("E:\\Projects\\fridges\\img\\photo.jpg");
        userRepository.save(testUser);

        byte[] photoBytes = userService.getUserPhoto(testUser.getEmail());

        assertNotNull(photoBytes);
        assertTrue(photoBytes.length > 0);
    }
}
