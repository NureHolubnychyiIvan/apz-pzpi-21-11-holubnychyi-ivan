package com.system.fridges.controllers;


import com.system.fridges.models.entities.User;
import com.system.fridges.models.transferObjects.foodObjects.FoodInFridge;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeOrder;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeTransactionHistory;
import com.system.fridges.service.FridgeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fridge")
public class FridgeController {

    @Autowired
    private FridgeServiceImpl fridgeService;

    @GetMapping("/foodInside")
    public ResponseEntity<List<FoodInFridge>> getAllFood(int fridgeId) {
        return ResponseEntity.ok(fridgeService.getFoodInFridgeById(fridgeId));
    }

    @GetMapping("/availableUser/{fridgeId}")
    public ResponseEntity<User> checkUserById(@PathVariable int fridgeId, int userId) {
        return ResponseEntity.ok(fridgeService.checkUserById(fridgeId, userId));
    }

    @GetMapping("/autoOrdering/{email}")
    public ResponseEntity<List<FridgeOrder>> getAutoOrdering(int fridgeId, @PathVariable String email) {
        return ResponseEntity.ok(fridgeService.getAutoOrdersById(fridgeId, email));
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<FridgeTransactionHistory>> getTransactionHistories(int fridgeId) {
        return ResponseEntity.ok(fridgeService.getTransactionHistoryById(fridgeId));
    }

    @PostMapping("/inventory")
    public void doInventory(@RequestBody Integer fridgeId) {
        fridgeService.doInventoryForFridge(fridgeId);
    }

    @PostMapping("/doAutoOrder")
    public void doAutoOrder(@RequestBody Integer fridgeId) {
        fridgeService.doAutoOrdering(fridgeId);
    }
}
