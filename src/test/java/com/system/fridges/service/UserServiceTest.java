package com.system.fridges.service;

import com.system.fridges.models.enam.UserType;
import com.system.fridges.models.entities.*;
import com.system.fridges.models.transferObjects.userObjects.UserFood;
import com.system.fridges.models.transferObjects.userObjects.UserOrder;
import com.system.fridges.models.transferObjects.userObjects.UserTransactionHistory;
import com.system.fridges.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private FridgeRepository fridgeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AutoOrderRepository autoOrderRepository;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private AuthenticationServiceImpl authenticationService;

    @Test
    void getFridgesByUserEmailTest() {
        String userEmail = "pasakane990@gmail.com";
        User user = new User("Den", "French", "Psf", "345",
                "pav45@gmail.com", "DF-345", null, "38095674546", UserType.REGULAR_USER);;
        when(userRepository.findUserByEmail(userEmail)).thenReturn(Optional.of(user));
        when(fridgeRepository.getFridgesByUserId(user.getUserId())).thenReturn(Optional.of(new ArrayList<Fridge>()));

        List<Fridge> fridges = userService.getFridgesByUserEmail(userEmail);

        assertEquals(0, fridges.size());

        verify(userRepository, times(1)).findUserByEmail(userEmail);
        verify(fridgeRepository, times(1)).getFridgesByUserId(user.getUserId());
    }

    @Test
    void getAllFoodUserByEmailTest() {
        String userEmail = "test@example.com";
        User user = new User("Den", "French", "Psf", "345",
                "pav45@gmail.com", "DF-345", null, "38095674546", UserType.REGULAR_USER);
        when(userRepository.findUserByEmail(userEmail)).thenReturn(java.util.Optional.of(user));

        List<UserFood> userFoods = userService.getAllFoodUserByEmail(userEmail);

        verify(userRepository, times(1)).findUserByEmail(userEmail);
        verify(foodRepository, times(1)).getAllFoodUserById(user.getUserId());
    }

    @Test
    void getTransactionHistoryByEmailTest() {
        String userEmail = "test@example.com";
        User user = new User("Den", "French", "Psf", "345",
                "pav45@gmail.com", "DF-345", null, "38095674546", UserType.REGULAR_USER);

        when(userRepository.findUserByEmail(userEmail)).thenReturn(Optional.of(user));

        List<UserTransactionHistory> transactionHistoryList = userService.getTransactionHistoryByEmail(userEmail);

        verify(userRepository, times(1)).findUserByEmail(userEmail);
        verify(transactionRepository, times(1)).getHistoryUsingByUserId(user.getUserId());
    }

    @Test
    void getAllOrdersForUserByEmailTest() {
        String userEmail = "test@example.com";
        User user = new User("Den", "French", "Psf", "345",
                "pav45@gmail.com", "DF-345", null, "38095674546", UserType.REGULAR_USER);

        List<UserOrder> userOrderList = userService.getAllOrdersForUserByEmail(userEmail);

        verify(userRepository, times(1)).findUserByEmail(userEmail);
        verify(subscriptionRepository, times(1)).getActualSubscriptionsForUser(user.getUserId());
    }

    @Test
    void getAllUsersTest() {
        List<User> userList = userService.getAllUsers();

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserByIdTest() {
        Integer userId = 1;
        User mockUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        User resultUser = userService.getUserById(userId);

        assertNotNull(resultUser);
        assertSame(mockUser, resultUser);

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getUserPhotoTest() {
        String userEmail = "pasakane990@gmail.com";

        byte[] resultPhoto = userService.getUserPhoto(userEmail);

        assertNull(resultPhoto);

        verify(userRepository, times(1)).findUserByEmail(userEmail);
    }

    @Test
    void saveUserPhoto() {
        User mockUser = new User("Den", "French", "Psf", "345",
                "pav45@gmail.com", "DF-345", null, "38095674546", UserType.REGULAR_USER);

        MultipartFile mockFile = mock(MultipartFile.class);

        userService.saveUser(mockUser, mockFile);

        verify(mockFile, times(1)).isEmpty();
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    void deleteUserTest() {
        String userEmail = "test@example.com";
        User mockUser = new User();
        when(userRepository.findUserByEmail(userEmail)).thenReturn(Optional.of(mockUser));

        userService.deleteUser(userEmail);

        verify(userRepository, times(1)).deleteById(mockUser.getUserId());
    }

    @Test
    void findUserByEmailTest() {
        String userEmail = "test@example.com";
        User mockUser = new User();
        when(userRepository.findUserByEmail(userEmail)).thenReturn(Optional.of(mockUser));

        User resultUser = userService.findUserByEmail(userEmail);

        assertNotNull(resultUser);
        assertSame(mockUser, resultUser);

        verify(userRepository, times(1)).findUserByEmail(userEmail);
    }

    @Test
    void hasActualSubscriptionTest() {
        String userEmail = "test@example.com";
        User mockUser = new User("Den", "French", "Psf", "345",
                "pav45@gmail.com", "DF-345", null, "38095674546", UserType.REGULAR_USER);;
        when(userRepository.findUserByEmail(userEmail)).thenReturn(Optional.of(mockUser));

        boolean hasSubscription = userService.hasActualSubscription(userEmail);

        assertFalse(hasSubscription);

        verify(userRepository, times(1)).findUserByEmail(userEmail);
        verify(subscriptionRepository, times(1)).getActualSubscriptionsForUser(mockUser.getUserId());
    }

    @Test
    void addTransactionTest() {
        Transaction mockTransaction = new Transaction();

        userService.addTransaction(mockTransaction);

        verify(transactionRepository, times(1)).save(mockTransaction);
    }

    @Test
    void addAutoOrdersTest() {
        AutoOrder mockOrder = new AutoOrder();

        userService.addAutoOrders(Collections.singletonList(mockOrder));

        verify(autoOrderRepository, times(1)).saveAll(Collections.singletonList(mockOrder));
    }

    @Test
    void addFoodTest() {
        Food mockFood = new Food();

        userService.addFood(Collections.singletonList(mockFood));

        verify(foodRepository, times(1)).saveAll(Collections.singletonList(mockFood));
    }

    @Test
    void addSubscriptionTest() {
        Subscription mockSubscription = new Subscription();

        userService.addSubscription(mockSubscription);

        verify(subscriptionRepository, times(1)).save(mockSubscription);
    }
}
