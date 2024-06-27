package com.system.fridges.repositories;


import com.system.fridges.models.transferObjects.fridgeObjects.FridgeOrder;
import com.system.fridges.models.transferObjects.userObjects.UserOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AutoOrderRepositoryTest {

    @Autowired
    private AutoOrderRepository autoOrderRepository;

    @Test
    @Transactional
    public void getInfoOrdersForFridgeByIdTest() {
        int fridgeId = 1;

        List<FridgeOrder> fridgeOrders = autoOrderRepository.getInfoOrdersForFridgeById(fridgeId);

        assertNotNull(fridgeOrders);
    }

    @Test
    @Transactional
    public void getInfoOrdersByIdWithNonFridgeTest() {
        int nonExistingFridgeId = -1;

        List<FridgeOrder> fridgeOrders = autoOrderRepository.getInfoOrdersForFridgeById(nonExistingFridgeId);

        assertNotNull(fridgeOrders);
        assertEquals(0, fridgeOrders.size(), "For a non-existing fridge, the list should be empty");
    }

    @Test
    @Transactional
    public void getAllOrdersForUserByIdTest() {
        int userId = 1;

        List<UserOrder> userOrders = autoOrderRepository.getAllOrdersForUserById(userId);

        assertNotNull(userOrders);
    }

    @Test
    @Transactional
    public void getAllOrdersForUserByIdWithNonUserTest() {
        int nonExistingUserId = -1;

        List<UserOrder> userOrders = autoOrderRepository.getAllOrdersForUserById(nonExistingUserId);

        assertNotNull(userOrders);
        assertEquals(0, userOrders.size(), "For a non-existing user, the list should be empty");
    }
}
