package com.system.fridges.service;


import com.system.fridges.models.entities.*;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeSpending;
import com.system.fridges.repositories.*;
import com.system.fridges.service.interfaces.AdminService;
import com.system.fridges.service.utils.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private FridgeRepository fridgeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AutoOrderRepository autoOrderRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean doBackupDatabase(byte[] backupPathHash) {
        byte[] decodedBytes = Base64.getDecoder().decode(backupPathHash);
        String backupPathDecoded = new String(decodedBytes, StandardCharsets.UTF_8);
        DatabaseManager databaseManager = new DatabaseManager();
        return databaseManager.backupSuccessful(backupPathDecoded);
    }

    @Override
    public boolean restoreDatabase(byte[] backupPathHash) {
        byte[] decodedBytes = Base64.getDecoder().decode(backupPathHash);
        String backupPathDecoded = new String(decodedBytes, StandardCharsets.UTF_8);
        DatabaseManager databaseManager = new DatabaseManager();
        return databaseManager.restoreSuccessful(backupPathDecoded);
    }

    @Override
    public List<FridgeSpending> getSpendingElectricity(float price, String nameCompany) {
        return fridgeRepository.getSpendingMoneyForEveryFridge(price, nameCompany);
    }

    @Override
    public float getSumSpending(float price, String nameCompany){
        return fridgeRepository.getSpendingMoneyAllFridges(price, nameCompany);
    }

    @Override
    public List<Fridge> getAllFridges() {
        return fridgeRepository.findAll();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Access> getAllAccess() {
        return accessRepository.findAll();
    }

    @Override
    public List<Office> getAllOffice() {
        return officeRepository.findAll();
    }

    @Override
    public List<Model> getAllModel() {
        return modelRepository.findAll();
    }

    @Override
    public List<Subscription> getAllSubscription() {
        return subscriptionRepository.findAll();
    }

    @Override
    public List<AutoOrder> getAllOrder() {
        return autoOrderRepository.findAll();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public void addFridges(List<Fridge> fridges) {
        fridgeRepository.saveAll(fridges);
    }

    @Override
    public void addUser(List<User> user) {
        userRepository.saveAll(user);
    }

    @Override
    public void addAccess(List<Access> access) {
        accessRepository.saveAll(access);
    }

    @Override
    public void addOffice(List<Office> office) {
        officeRepository.saveAll(office);
    }

    @Override
    public void addModel(List<Model> model) {
        modelRepository.saveAll(model);
    }

    @Override
    public void addSubscription(List<Subscription> subscription) {
        subscriptionRepository.saveAll(subscription);
    }

    @Override
    public void addProduct( List<Product> product) {
        productRepository.saveAll(product);
    }

    @Override
    public void addAutoOrder(List<AutoOrder> autoOrders) {
        autoOrderRepository.saveAll(autoOrders);
    }

    @Override
    public void addFood( List<Food> food) {
        foodRepository.saveAll(food);
    }

    @Override
    public void addTransaction( List<Transaction> transaction) {
        transactionRepository.saveAll(transaction);
    }

    @Override
    public void deleteUser(List<Integer> user) {
        userRepository.deleteAllById(user);
    }

    @Override
    public void deleteFridge(List<Integer> fridge) {
        fridgeRepository.deleteAllById(fridge);
    }

    @Override
    public void deleteAccess( List<Integer> access) {
        accessRepository.deleteAllById(access);
    }

    @Override
    public void deleteOffice( List<Integer> office) {
        officeRepository.deleteAllById(office);
    }

    @Override
    public void deleteModel( List<Integer> model) {
        modelRepository.deleteAllById(model);
    }

    @Override
    public void deleteSubscription(List<Integer> subscription) {
        subscriptionRepository.deleteAllById(subscription);
    }

    @Override
    public void deleteProduct( List<Integer> product) {
        productRepository.deleteAllById(product);
    }

    @Override
    public void deleteAutoOrder( List<Integer> autoOrder) {
        autoOrderRepository.deleteAllById(autoOrder);
    }

    @Override
    public void deleteFood(List<Integer> food) {
        foodRepository.deleteAllById(food);
    }

    @Override
    public void deleteTransaction( List<Integer> transaction) {
        transactionRepository.deleteAllById(transaction);
    }
}
