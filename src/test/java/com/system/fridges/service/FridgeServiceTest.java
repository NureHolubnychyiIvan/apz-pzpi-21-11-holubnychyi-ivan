package com.system.fridges.service;


import com.system.fridges.models.entities.Access;
import com.system.fridges.models.entities.Fridge;
import com.system.fridges.models.entities.User;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeOrder;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeTransactionHistory;
import com.system.fridges.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class FridgeServiceTest {

    @InjectMocks
    private FridgeServiceImpl fridgeService;

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
    private AccessRepository accessRepository;

    @Test
    void checkUserByIdTest() {
        int fridgeId = 1;
        int userId = 1;

        when(accessRepository.findAllAccessForUserById(userId)).thenReturn(new ArrayList<Access>());
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));

        fridgeService.checkUserById(fridgeId, userId);

        verify(accessRepository, times(1)).findAllAccessForUserById(userId);
    }

    @Test
    void getFridgeByIdReturnsFridge() {
        int fridgeId = 1;

        when(fridgeRepository.findById(fridgeId)).thenReturn(Optional.of(new Fridge()));

        fridgeService.getFridgeById(fridgeId);

        verify(fridgeRepository, times(1)).findById(fridgeId);
    }

    @Test
    void saveFridgeTest() {
        Fridge fridgeToSave = new Fridge();

        fridgeService.saveFridge(fridgeToSave);


        verify(fridgeRepository, times(1)).save(fridgeToSave);
    }

    @Test
    void deleteFridgeByIdTest() {
        int fridgeId = 1;

        fridgeService.deleteFridgeById(fridgeId);

        verify(fridgeRepository, times(1)).deleteById(fridgeId);
    }

    @Test
    void getFoodInFridgeByIdTest() {
        int fridgeId = 1;

        fridgeService.getFoodInFridgeById(fridgeId);

        verify(foodRepository, times(1)).getAllFoodForFridge(fridgeId);
    }

    @Test
    void getTransactionHistoryByIdTest() {
        int fridgeId = 1;

        List<FridgeTransactionHistory> actualHistoryList = fridgeService.getTransactionHistoryById(fridgeId);

        verify(transactionRepository, times(1)).getHistoryUsingFridge(fridgeId);
    }

    @Test
    void getAutoOrdersByIdTest() {
        int fridgeId = 1;
        String email = "pasakane990@gmail.com";
        User user = new User();

        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(user));

        List<FridgeOrder> actualOrders = fridgeService.getAutoOrdersById(fridgeId, email);

        assertEquals(0, actualOrders.size());

        verify(userRepository, times(1)).findUserByEmail(email);
        verify(subscriptionRepository, times(1)).getActualSubscriptionsForUser(user.getUserId());
    }

    @Test
    void doInventoryForFridgeTest() {
        int fridgeId = 1;
        Fridge fridge = new Fridge();

        when(fridgeRepository.findById(fridgeId)).thenReturn(Optional.of(fridge));

        fridgeService.doInventoryForFridge(fridgeId);

        verify(fridgeRepository, times(1)).findById(fridgeId);
        verify(foodRepository, times(1)).getSpoiledFoodByFridgeId(fridge.getFridgeId());
    }

    @Test
    void testDoAutoOrdering() {
        int fridgeId = 1;

        fridgeService.doAutoOrdering(fridgeId);

        verify(autoOrderRepository, times(1)).findAll();
    }
}
