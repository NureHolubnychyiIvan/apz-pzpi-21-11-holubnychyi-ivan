package com.system.fridges.service.functional;

import com.system.fridges.models.entities.Access;
import com.system.fridges.models.entities.Fridge;
import com.system.fridges.models.entities.User;
import com.system.fridges.models.transferObjects.foodObjects.FoodInFridge;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeOrder;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeTransactionHistory;
import com.system.fridges.repositories.*;
import com.system.fridges.service.FridgeServiceImpl;
import com.system.fridges.service.utils.EmailSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FridgeServiceFunctionalTest {

    @Autowired
    private FridgeServiceImpl fridgeService;

    @Autowired
    private FridgeRepository fridgeRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private AutoOrderRepository autoOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccessRepository accessRepository;

    private EmailSender emailSender = new EmailSender();

    @Test
    void checkUserByIdTest() {
        User user = userRepository.findById(1).get();

        Fridge fridge = fridgeRepository.findById(1).get();

        Access access = new Access();
        access.setUser(user);
        access.setFridge(fridge);

        User result = fridgeService.checkUserById(fridge.getFridgeId(), user.getUserId());

        assertNotNull(result);
        assertEquals(user.getUserId(), result.getUserId());
    }

    @Test
    void getFridgeByIdTest() {
        Fridge fridge = fridgeRepository.findById(1).get();

        Fridge result = fridgeService.getFridgeById(fridge.getFridgeId());

        assertNotNull(result);
        assertEquals(fridge.getFridgeId(), result.getFridgeId());
    }

    @Test
    void saveFridgeTest() {
        Fridge fridge = fridgeRepository.findById(1).get();

        Fridge result = fridgeRepository.findById(fridge.getFridgeId()).orElse(null);

        assertNotNull(result);
        assertEquals(fridge.getFridgeId(), result.getFridgeId());
    }

    @Test
    void deleteFridgeByIdTest() {
        Fridge fridge = fridgeRepository.findById(3).get();

        fridgeService.deleteFridgeById(fridge.getFridgeId());

        Fridge result = fridgeRepository.findById(fridge.getFridgeId()).orElse(null);

        assertNull(result);
    }

    @Test
    void getFoodInFridgeByIdTest() {
        Fridge fridge = fridgeRepository.findById(1).get();

        List<FoodInFridge> result = fridgeService.getFoodInFridgeById(fridge.getFridgeId());

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getTransactionHistoryByIdTest() {
        Fridge fridge = fridgeRepository.findById(1).get();

        List<FridgeTransactionHistory> result = fridgeService.getTransactionHistoryById(fridge.getFridgeId());

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getAutoOrdersByIdTest() {
        Fridge fridge = fridgeRepository.findById(1).get();

        User user = userRepository.findById(1).get();

        List<FridgeOrder> result = fridgeService.getAutoOrdersById(fridge.getFridgeId(), user.getEmail());

        assertNotNull(result);
        assertEquals(autoOrderRepository.getAllOrdersForUserById(user.getUserId()).size(), result.size());
    }

    @Test
    void doInventoryForFridgeTest() {
       int fridgeId = 1;

        fridgeService.doInventoryForFridge(fridgeId);
    }

    @Test
    void doAutoOrderingTest() {
        int fridgeId = 1;

        fridgeService.doAutoOrdering(fridgeId);
    }
}
