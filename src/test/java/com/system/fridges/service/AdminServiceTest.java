package com.system.fridges.service;


import com.system.fridges.models.entities.*;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeSpending;
import com.system.fridges.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private FridgeRepository fridgeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AutoOrderRepository autoOrderRepository;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private OfficeRepository officeRepository;

    @Mock
    private ModelRepository modelRepository;

    @Mock
    private AccessRepository accessRepository;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void doBackupDatabaseTest() {
        String backupPath = "E:\\Projects\\fridges\\database\\backup.sql";
        byte[] backupPathHash = Base64.getEncoder().encode(backupPath.getBytes());
        boolean result = adminService.doBackupDatabase(backupPathHash);
        assertTrue(result);
    }

    @Test
    public void restoreDatabaseTest() {
        String backupPath = "E:\\Projects\\fridges\\database\\backup.sql";
        byte[] backupPathHash = Base64.getEncoder().encode(backupPath.getBytes());
        boolean result = adminService.restoreDatabase(backupPathHash);
        assertTrue(result);
    }

    @Test
    public void getSpendingElectricityReturnsStatistic() {
        int price = 100;
        String nameCompany = "Epam";
        List<FridgeSpending> expectedSpendingList = List.of(new FridgeSpending(1, "Epam", 23000.0, 10.0));
        when(fridgeRepository.getSpendingMoneyForEveryFridge(price, nameCompany)).thenReturn(expectedSpendingList);

        List<FridgeSpending> result = adminService.getSpendingElectricity(price, nameCompany);

        assertEquals(expectedSpendingList, result);
    }

    @Test
    public void getSumSpendingReturnsSum() {
        float price = 100.0f;
        String nameCompany = "ExampleCompany";
        float expectedSumSpending = 500.0f;
        when(fridgeRepository.getSpendingMoneyAllFridges(price, nameCompany)).thenReturn(expectedSumSpending);

        float result = adminService.getSumSpending(price, nameCompany);

        assertEquals(expectedSumSpending, result, 0.01);
    }

    @Test
    public void getAllFridgesReturnsFridges() {
        List<Fridge> expectedFridges = Arrays.asList(new Fridge(), new Fridge());
        when(fridgeRepository.findAll()).thenReturn(expectedFridges);

        List<Fridge> result = adminService.getAllFridges();

        assertEquals(expectedFridges, result);
    }

    @Test
    void getAllUsersReturnsUsers() {
        List<User> expectedUsers = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> result = adminService.getAllUsers();

        assertEquals(expectedUsers, result);
    }

    @Test
    void getAllAccessReturnsAccess() {
        List<Access> expectedAccess = Arrays.asList(new Access(), new Access());
        when(accessRepository.findAll()).thenReturn(expectedAccess);

        List<Access> result = adminService.getAllAccess();

        assertEquals(expectedAccess, result);
    }

    @Test
    void getAllOfficeReturnsOffice() {
        List<Office> expectedAccess = Arrays.asList(new Office(), new Office());
        when(officeRepository.findAll()).thenReturn(expectedAccess);

        List<Office> result = adminService.getAllOffice();

        assertEquals(expectedAccess, result);
    }

    @Test
    void getAllModelReturnsModel() {
        List<Model> expectedAccess = Arrays.asList(new Model(), new Model());
        when(modelRepository.findAll()).thenReturn(expectedAccess);

        List<Model> result = adminService.getAllModel();

        assertEquals(expectedAccess, result);
    }

    @Test
    void getAllSubscriptionReturnsSubscription() {
        List<Subscription> expectedAccess = Arrays.asList(new Subscription(), new Subscription());
        when(subscriptionRepository.findAll()).thenReturn(expectedAccess);

        List<Subscription> result = adminService.getAllSubscription();

        assertEquals(expectedAccess, result);
    }

    @Test
    void getAllOrderReturnsOrder() {
        List<AutoOrder> expectedAccess = Arrays.asList(new AutoOrder(), new AutoOrder());
        when(autoOrderRepository.findAll()).thenReturn(expectedAccess);

        List<AutoOrder> result = adminService.getAllOrder();

        assertEquals(expectedAccess, result);
    }

    @Test
    void getAllOrderReturnsProduct() {
        List<Product> expectedAccess = Arrays.asList(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(expectedAccess);

        List<Product> result = adminService.getAllProduct();

        assertEquals(expectedAccess, result);
    }

    @Test
    void getAllFoodReturnsFood() {
        List<Food> expectedAccess = Arrays.asList(new Food(), new Food());
        when(foodRepository.findAll()).thenReturn(expectedAccess);

        List<Food> result = adminService.getAllFood();

        assertEquals(expectedAccess, result);
    }

    @Test
    void getAllTransactionReturnsTransaction() {
        List<Transaction> expectedAccess = Arrays.asList(new Transaction(), new Transaction());
        when(transactionRepository.findAll()).thenReturn(expectedAccess);

        List<Transaction> result = adminService.getAllTransaction();

        assertEquals(expectedAccess, result);
    }

    @Test
    public void addFridgesTest() {
        List<Fridge> fridges = Arrays.asList(new Fridge(), new Fridge());

        adminService.addFridges(fridges);

        verify(fridgeRepository, times(1)).saveAll(fridges);
    }

    @Test
    public void addUsersTest() {
        List<User> users = Arrays.asList(new User(), new User());

        adminService.addUser(users);

        verify(userRepository, times(1)).saveAll(users);
    }

    @Test
    public void addAccessTest() {
        List<Access> accesses = Arrays.asList(new Access(), new Access());

        adminService.addAccess(accesses);

        verify(accessRepository, times(1)).saveAll(accesses);
    }

    @Test
    public void addOfficeTest() {
        List<Office> offices = Arrays.asList(new Office(), new Office());

        adminService.addOffice(offices);

        verify(officeRepository, times(1)).saveAll(offices);
    }

    @Test
    public void addModelTest() {
        List<Model> models = Arrays.asList(new Model(), new Model());

        adminService.addModel(models);

        verify(modelRepository, times(1)).saveAll(models);
    }

    @Test
    public void addSubscriptionTest() {
        List<Subscription> subscriptions = Arrays.asList(new Subscription(), new Subscription());

        adminService.addSubscription(subscriptions);

        verify(subscriptionRepository, times(1)).saveAll(subscriptions);
    }

    @Test
    public void addProductTest() {
        List<Product> products = Arrays.asList(new Product(), new Product());

        adminService.addProduct(products);

        verify(productRepository, times(1)).saveAll(products);
    }

    @Test
    public void addAutoOrderTest() {
        List<AutoOrder> autoOrders = Arrays.asList(new AutoOrder(), new AutoOrder());

        adminService.addAutoOrder(autoOrders);

        verify(autoOrderRepository, times(1)).saveAll(autoOrders);
    }

    @Test
    public void addFoodTest() {
        List<Food> foods = Arrays.asList(new Food(), new Food());

        adminService.addFood(foods);

        verify(foodRepository, times(1)).saveAll(foods);
    }

    @Test
    public void addTransactionTest() {
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());

        adminService.addTransaction(transactions);

        verify(transactionRepository, times(1)).saveAll(transactions);
    }

    @Test
    public void deleteUserTest() {
        List<Integer> userIds = Arrays.asList(1, 2, 3);

        adminService.deleteUser(userIds);

        verify(userRepository, times(1)).deleteAllById(userIds);
    }

    @Test
    public void deleteFridgeTest() {
        List<Integer> fridgeIds = Arrays.asList(1, 2, 3);

        adminService.deleteFridge(fridgeIds);

        verify(fridgeRepository, times(1)).deleteAllById(fridgeIds);
    }

    @Test
    public void deleteAccessTest() {
        List<Integer> accessIds = Arrays.asList(1, 2, 3);

        adminService.deleteAccess(accessIds);

        verify(accessRepository, times(1)).deleteAllById(accessIds);
    }

    @Test
    public void deleteOfficeTest() {
        List<Integer> officeIds = Arrays.asList(1, 2, 3);

        adminService.deleteOffice(officeIds);

        verify(officeRepository, times(1)).deleteAllById(officeIds);
    }

    @Test
    public void deleteModelTest() {
        List<Integer> modelIds = Arrays.asList(1, 2, 3);

        adminService.deleteModel(modelIds);

        verify(modelRepository, times(1)).deleteAllById(modelIds);
    }

    @Test
    public void deleteSubscriptionTest() {
        List<Integer> subscriptionIds = Arrays.asList(1, 2, 3);

        adminService.deleteSubscription(subscriptionIds);

        verify(subscriptionRepository, times(1)).deleteAllById(subscriptionIds);
    }

    @Test
    public void deleteProductTest() {
        List<Integer> productIds = Arrays.asList(1, 2, 3);

        adminService.deleteProduct(productIds);

        verify(productRepository, times(1)).deleteAllById(productIds);
    }

    @Test
    public void deleteAutoOrderTest() {
        List<Integer> autoOrderIds = Arrays.asList(1, 2, 3);

        adminService.deleteAutoOrder(autoOrderIds);

        verify(autoOrderRepository, times(1)).deleteAllById(autoOrderIds);
    }

    @Test
    public void deleteFoodTest() {
        List<Integer> foodIds = Arrays.asList(1, 2, 3);

        adminService.deleteFood(foodIds);

        verify(foodRepository, times(1)).deleteAllById(foodIds);
    }

    @Test
    public void deleteTransactionTest() {
        List<Integer> transactionIds = Arrays.asList(1, 2, 3);

        adminService.deleteTransaction(transactionIds);

        verify(transactionRepository, times(1)).deleteAllById(transactionIds);
    }
}
