package com.system.fridges.controllers;


import com.system.fridges.models.entities.*;
import com.system.fridges.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/databaseAdmin")
public class DatabaseAdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/restore")
    public ResponseEntity<Boolean> restoreDatabase(byte[] backupPathHash) {
         return ResponseEntity.ok(adminService.restoreDatabase(backupPathHash));
    }

    @PostMapping("/backup")
    public ResponseEntity<Boolean> backupDatabase(byte[] backupPathHash) {
        return ResponseEntity.ok(adminService.doBackupDatabase(backupPathHash));
    }

    @GetMapping("/allFridges")
    public ResponseEntity<List<Fridge>> getAllFridges() {
        return ResponseEntity.ok(adminService.getAllFridges());
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/allAccess")
    public ResponseEntity<List<Access>> getAllAccess() {
        return ResponseEntity.ok(adminService.getAllAccess());
    }

    @GetMapping("/allOffice")
    public ResponseEntity<List<Office>> getAllOffices() {
        return ResponseEntity.ok(adminService.getAllOffice());
    }

    @GetMapping("/allModel")
    public ResponseEntity<List<Model>> getAllModels() {
        return ResponseEntity.ok(adminService.getAllModel());
    }

    @GetMapping("/allSubscription")
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        return ResponseEntity.ok(adminService.getAllSubscription());
    }

    @GetMapping("/allAutoOrder")
    public ResponseEntity<List<AutoOrder>> getAllOrders() {
        return ResponseEntity.ok(adminService.getAllOrder());
    }

    @GetMapping("/allProduct")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(adminService.getAllProduct());
    }

    @GetMapping("/allFood")
    public ResponseEntity<List<Food>> getAllFood() {
        return ResponseEntity.ok(adminService.getAllFood());
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(adminService.getAllTransaction());
    }

    @PostMapping("/addFridges")
    public void addFridges(@RequestBody List<Fridge> fridges) {
        adminService.addFridges(fridges);
    }

    @PostMapping("/addUser")
    public void addUsers(@RequestBody List<User> user) {
        adminService.addUser(user);
    }

    @PostMapping("/addAccess")
    public void addAccess(@RequestBody List<Access> access) {
        adminService.addAccess(access);
    }

    @PostMapping("/addOffice")
    public void addOffices(@RequestBody List<Office> office) {
        adminService.addOffice(office);
    }

    @PostMapping("/addModel")
    public void addModels(@RequestBody List<Model> model) {
        adminService.addModel(model);
    }

    @PostMapping("/addSubscription")
    public void addSubscriptions(@RequestBody List<Subscription> subscription) {
        adminService.addSubscription(subscription);
    }

    @PostMapping("/addProduct")
    public void addProducts(@RequestBody List<Product> product) {
        adminService.addProduct(product);
    }

    @PostMapping("/addAutoOrder")
    public void addAutoOrders(@RequestBody List<AutoOrder> autoOrders) {
        adminService.addAutoOrder(autoOrders);
    }

    @PostMapping("/addFood")
    public void addFood(@RequestBody List<Food> food) {
        adminService.addFood(food);
    }

    @PostMapping("/addTransaction")
    public void addTransactions(@RequestBody List<Transaction> transaction) {
        adminService.addTransaction(transaction);
    }

    @PostMapping("/deleteUsers")
    public void deleteUsers(@RequestBody List<Integer> user) {
        adminService.deleteUser(user);
    }

    @PostMapping("/deleteFridges")
    public void deleteFridges(@RequestBody List<Integer> fridge) {
        adminService.deleteFridge(fridge);
    }

    @PostMapping("/deleteAccess")
    public void deleteAccess(@RequestBody List<Integer> access) {
        adminService.deleteAccess(access);
    }

    @PostMapping("/deleteOffice")
    public void deleteOffices(@RequestBody List<Integer> office) {
        adminService.deleteOffice(office);
    }

    @PostMapping("/deleteModel")
    public void deleteModels(@RequestBody List<Integer> model) {
        adminService.deleteModel(model);
    }

    @PostMapping("/deleteSubscription")
    public void deleteSubscriptions(@RequestBody List<Integer> subscription) {
        adminService.deleteSubscription(subscription);
    }

    @PostMapping("/deleteProduct")
    public void deleteProducts(@RequestBody List<Integer> product) {
        adminService.deleteProduct(product);
    }

    @PostMapping("/deleteAutoOrder")
    public void deleteAutoOrders(@RequestBody List<Integer> autoOrder) {
        adminService.deleteAutoOrder(autoOrder);
    }

    @PostMapping("/deleteFood")
    public void deleteFood(@RequestBody List<Integer> food) {
        adminService.deleteFood(food);
    }

    @PostMapping("/deleteTransaction")
    public void deleteTransactions(@RequestBody List<Integer> transaction) {
        adminService.deleteTransaction(transaction);
    }
}
