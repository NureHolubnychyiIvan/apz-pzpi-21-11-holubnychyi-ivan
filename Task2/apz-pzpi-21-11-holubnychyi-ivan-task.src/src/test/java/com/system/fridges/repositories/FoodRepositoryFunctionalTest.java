package com.system.fridges.repositories;

import com.system.fridges.models.transferObjects.foodObjects.FoodInFridge;
import com.system.fridges.models.transferObjects.foodObjects.SpoiledFood;
import com.system.fridges.models.transferObjects.userObjects.UserFood;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FoodRepositoryFunctionalTest {

    @Autowired
    private FoodRepository foodRepository;

    @Test
    @Transactional
    public void getAllFoodUserByIdTest() {
        int userId = 1;

        List<UserFood> userFoods = foodRepository.getAllFoodUserById(userId);

        assertNotNull(userFoods);
    }

    @Test
    @Transactional
    public void getAllFoodUserByIdWithNonUserTest() {
        int nonExistingUserId = -1;

        List<UserFood> userFoods = foodRepository.getAllFoodUserById(nonExistingUserId);

        assertNotNull(userFoods);
        assertEquals(0, userFoods.size(), "For a non-existing user, the list should be empty");
    }

    @Test
    @Transactional
    public void getSpoiledFoodByFridgeIdTest() {
        int fridgeId = 1;

        List<SpoiledFood> spoiledFoods = foodRepository.getSpoiledFoodByFridgeId(fridgeId);

        assertNotNull(spoiledFoods);
    }

    @Test
    @Transactional
    public void getSpoiledFoodByFridgeIdWithNonFridgeTest() {
        int nonExistingFridgeId = -1;

        List<SpoiledFood> spoiledFoods = foodRepository.getSpoiledFoodByFridgeId(nonExistingFridgeId);

        assertNotNull(spoiledFoods);
        assertEquals(0, spoiledFoods.size(), "For a non-existing fridge, the list should be empty");
    }

    @Test
    @Transactional
    public void getAllFoodForFridgeTest() {
        int fridgeId = 1;

        List<FoodInFridge> foodInFridges = foodRepository.getAllFoodForFridge(fridgeId);

        assertNotNull(foodInFridges);
    }

    @Test
    @Transactional
    public void getAllFoodForFridgeWithNonFridgeTest() {
        int nonExistingFridgeId = -1;

        List<FoodInFridge> foodInFridges = foodRepository.getAllFoodForFridge(nonExistingFridgeId);

        assertNotNull(foodInFridges);
        assertEquals(0, foodInFridges.size(), "For a non-existing fridge, the list should be empty");
    }
}
