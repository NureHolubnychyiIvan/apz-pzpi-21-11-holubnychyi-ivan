package com.system.fridges.service.interfaces;

import com.system.fridges.models.entities.*;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeSpending;

import java.util.List;

public interface AdminService {

    public boolean doBackupDatabase(byte[] backupPathHash);

    public boolean restoreDatabase(byte[] backupPathHash);

     List<FridgeSpending> getSpendingElectricity(float price, String nameCompany);

     float getSumSpending(float price, String nameCompany);

    List<Fridge> getAllFridges() ;

     List<User> getAllUsers();

     List<Access> getAllAccess();

    List<Office> getAllOffice();

    List<Model> getAllModel();

     List<Subscription> getAllSubscription();

    List<AutoOrder> getAllOrder();

    List<Product> getAllProduct();

    List<Food> getAllFood();

    List<Transaction> getAllTransaction();

    void addFridges(List<Fridge> fridges);

    void addUser(List<User> user);

     void addAccess(List<Access> access);

     void addOffice(List<Office> office);

     void addModel(List<Model> model);

     void addSubscription(List<Subscription> subscription);

     void addProduct( List<Product> product);

    void addAutoOrder(List<AutoOrder> autoOrders);

    void addFood( List<Food> food);


     void addTransaction( List<Transaction> transaction);

     void deleteUser(List<Integer> user);

     void deleteFridge(List<Integer> fridge);

    void deleteAccess( List<Integer> access);

     void deleteOffice( List<Integer> office);

    void deleteModel( List<Integer> model);

    void deleteSubscription(List<Integer> subscription);

     void deleteProduct( List<Integer> product);

     void deleteAutoOrder( List<Integer> autoOrder);

     void deleteFood(List<Integer> food);

     void deleteTransaction( List<Integer> transaction);
}
