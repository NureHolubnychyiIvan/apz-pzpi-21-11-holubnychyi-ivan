package com.system.fridges.controllers;

import com.system.fridges.models.entities.*;
import com.system.fridges.models.transferObjects.userObjects.UserFood;
import com.system.fridges.models.transferObjects.userObjects.UserOrder;
import com.system.fridges.models.transferObjects.userObjects.UserTransactionHistory;
import com.system.fridges.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/account/{email}/update")
    public void updateUserAccount(@PathVariable String email, @RequestParam("file") MultipartFile file) {
        userService.saveUser(userService.findUserByEmail(email), file);
    }

    @GetMapping("/account/{email}")
    public ResponseEntity<User> getPresentUser(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @GetMapping("/photo/{email}")
    public ResponseEntity<byte[]> getUserPhoto(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserPhoto(email));
    }

    @PostMapping("/account/{email}/delete")
    public void deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
    }

    @GetMapping("/history/orders/{email}")
    public ResponseEntity<List<UserOrder>> getAllOrders(@PathVariable String email) {
        return ResponseEntity.ok(userService.getAllOrdersForUserByEmail(email));
    }

    @GetMapping("/historyUsing/{email}")
    public ResponseEntity<List<UserTransactionHistory>> getHistoryUsing(@PathVariable String email) {
        return ResponseEntity.ok(userService.getTransactionHistoryByEmail(email));
    }

    @GetMapping("/food/{email}")
    public ResponseEntity<List<UserFood>> getUserFood(@PathVariable String email) {
        return ResponseEntity.ok(userService.getAllFoodUserByEmail(email));
    }

    @GetMapping("/fridges/{email}")
    public ResponseEntity<List<Fridge>> getAvailableFridges(@PathVariable String email) {;
        return ResponseEntity.ok(userService.getFridgesByUserEmail(email));
    }

    @GetMapping("/subscription/{email}")
    public ResponseEntity<Boolean> hasActualSubscription(@PathVariable String email) {
        return ResponseEntity.ok(userService.hasActualSubscription(email));
    }

    @PostMapping("/addTransaction")
    public void addTransaction(@RequestBody Transaction transaction) {
        userService.addTransaction(transaction);
    }

    @PostMapping("/autoOrdering/addOrder")
    public void addOrders(@RequestBody List<AutoOrder> orders) {
        userService.addAutoOrders(orders);
    }

    @PostMapping("/addFood")
    public void addFood(@RequestBody List<Food> food) {
        userService.addFood(food);
    }

    @PostMapping("/addSubscription")
    public void addSubscription(@RequestBody Subscription subscription) {
        userService.addSubscription(subscription);
    }
}
