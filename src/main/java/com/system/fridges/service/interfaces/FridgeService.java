package com.system.fridges.service.interfaces;

import com.system.fridges.models.entities.Fridge;
import com.system.fridges.models.entities.User;
import com.system.fridges.models.transferObjects.foodObjects.FoodInFridge;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeOrder;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeTransactionHistory;

import java.util.List;

public interface FridgeService {

    User checkUserById(int fridgeId, int userId);

    //    List<Fridge> getFridgesByUserId(int userId);
    Fridge getFridgeById(int fridgeId);

    void saveFridge(Fridge fridge);

    void deleteFridgeById(int fridgeId);

    List<FoodInFridge> getFoodInFridgeById(int fridgeId);

    List<FridgeTransactionHistory> getTransactionHistoryById(int fridgeId);

    List<FridgeOrder> getAutoOrdersById(int fridgeId, String email);

    void doInventoryForFridge(Integer fridgeId);

    void doAutoOrdering(int fridgeId);

//    void doInventoryFridgesInCompany(String nameCompany);
}
