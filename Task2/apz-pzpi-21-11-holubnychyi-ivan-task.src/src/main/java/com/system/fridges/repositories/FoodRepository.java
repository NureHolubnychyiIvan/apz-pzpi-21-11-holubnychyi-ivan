package com.system.fridges.repositories;

import com.system.fridges.models.entities.Food;
import com.system.fridges.models.transferObjects.foodObjects.FoodInFridge;
import com.system.fridges.models.transferObjects.foodObjects.SpoiledFood;
import com.system.fridges.models.transferObjects.userObjects.UserFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    @Query(name = "UserFoodQuery", nativeQuery = true)
    List<UserFood> getAllFoodUserById(@Param("userId") int userId);

    @Query(nativeQuery = true, name = "SpoiledFoodQuery")
    List<SpoiledFood> getSpoiledFoodByFridgeId(@Param("fridgeId") int fridgeId);

    @Query(nativeQuery = true, name = "FoodInFridgeQuery")
    List<FoodInFridge> getAllFoodForFridge(@Param("fridgeId") int fridgeId);

}


