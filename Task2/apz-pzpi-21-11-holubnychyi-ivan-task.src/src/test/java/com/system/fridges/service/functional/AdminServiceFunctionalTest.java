package com.system.fridges.service.functional;


import com.system.fridges.models.enam.UserType;
import com.system.fridges.models.entities.*;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeSpending;
import com.system.fridges.repositories.*;
import com.system.fridges.service.AdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminServiceFunctionalTest {

    @Autowired
    private AdminServiceImpl adminService;

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
        List<FridgeSpending> expectedSpendingList = List.of(new FridgeSpending(1, "AG234", 23000.0, 2300000.0), new FridgeSpending(4, "AG234", 23000.0, 2300000.0));

        List<FridgeSpending> result = adminService.getSpendingElectricity(price, nameCompany);

        assertEquals(expectedSpendingList, result);
    }

    @Test
    public void getSumSpendingReturnsSum() {
        float price = 100.0f;
        String nameCompany = "Epam";
        float expectedSumSpending = 4600000.0f;

        float result = adminService.getSumSpending(price, nameCompany);

        assertEquals(expectedSumSpending, result, 0.01);
    }

    @Test
    @Transactional
    public void getAllUsersReturnsUsers() {
        List<User> allUsers = adminService.getAllUsers();

        assertEquals(1, allUsers.size());
    }

    @Test
    @Transactional
    public void getAllFridgesReturnsFridges() {
        List<Fridge> allFridges = adminService.getAllFridges();

        assertEquals(fridgeRepository.findAll().size(), allFridges.size());
    }

    @Test
    @Transactional
    public void getAllAccessReturnsAccess() {
        List<Access> allAccess = adminService.getAllAccess();

        assertEquals(1, allAccess.size());
    }

    @Test
    @Transactional
    public void getAllOfficeReturnsOffice() {
        List<Office> allOffice = adminService.getAllOffice();

        assertEquals(1, allOffice.size());
    }

    @Test
    @Transactional
    public void getAllModelReturnsModel() {
        List<Model> allModel = adminService.getAllModel();

        assertEquals(1, allModel.size());
    }

    @Test
    @Transactional
    public void getAllSubscriptionReturnsSubscription() {
        List<Subscription> allSubscription = adminService.getAllSubscription();

        assertEquals(1, allSubscription.size());
    }

    @Test
    @Transactional
    public void getAllOrderReturnsOrder() {
        List<AutoOrder> allAutoOrder = adminService.getAllOrder();

        assertEquals(autoOrderRepository.findAll().size(), allAutoOrder.size());
    }

    @Test
    @Transactional
    public void getAllProductReturnsProduct() {
        List<Product> allProduct = adminService.getAllProduct();

        assertEquals(1, allProduct.size());
    }

    @Test
    @Transactional
    public void getAllFoodReturnsFood() {
        List<Food> allFood = adminService.getAllFood();

        assertEquals(2, allFood.size());
    }

    @Test
    @Transactional
    public void getAllTransactionReturnsTransaction() {
        List<Transaction> allTransaction = adminService.getAllTransaction();

        assertEquals(1, allTransaction.size());
    }

    @Test
    @Transactional
    public void addUsersTest() {
        User user = new User("Den", "French", "Psf", "345",
                "pav45@gmail.com", "DF-345", null, "38095674546", UserType.REGULAR_USER);

        adminService.addUser(List.of(user));

        assertEquals(2, adminService.getAllUsers().size());
    }

    @Test
    @Transactional
    public void addSubscriptionTest() {
        Subscription subscription = new Subscription(new Date(), new Date(), 456, userRepository.findById(1).get());

        adminService.addSubscription(List.of(subscription));

        assertEquals(2, adminService.getAllSubscription().size());
    }

    @Test
    @Transactional
    public void addModelTest() {
        Model model = new Model("AF-45", "LG", 34, 2400);

        adminService.addModel(List.of(model));

        assertEquals(2, adminService.getAllModel().size());
    }

    @Test
    @Transactional
    public void addOfficeTest() {
        Office office = new Office("USA", "ppoiu", "rty","fgh", "45g", "Sigma");

        adminService.addOffice(List.of(office));

        assertEquals(2, adminService.getAllOffice().size());
    }

    @Test
    @Transactional
    public void addFridgesTest() {
        Fridge fridge = new Fridge(4.0f, 20.0f, officeRepository.findById(1).get(), modelRepository.findById(1).get());

        adminService.addFridges(List.of(fridge));

        assertEquals(fridgeRepository.findAll().size(), adminService.getAllFridges().size());
    }

    @Test
    @Transactional
    public void addAccessTest() {
        Access access = new Access(userRepository.findById(1).get(), fridgeRepository.findById(4).get());

        adminService.addAccess(List.of(access));

        assertEquals(accessRepository.findAll().size(), adminService.getAllAccess().size());
    }

    @Test
    @Transactional
    public void addProductTest() {
        Product product = new Product("Peach", 300f);

        adminService.addProduct(List.of(product));

        assertEquals(2, adminService.getAllProduct().size());
    }

    @Test
    @Transactional
    public void addOrderTest() {
        AutoOrder autoOrder = new AutoOrder(LocalDateTime.now(), 4, accessRepository.findById(1).get(), productRepository.findById(1).get());

        adminService.addAutoOrder(List.of(autoOrder));

        assertEquals(autoOrderRepository.findAll().size(), adminService.getAllOrder().size());
    }

    @Test
    @Transactional
    public void addTransactionTest() {
        Transaction transaction = new Transaction(LocalDateTime.now(), LocalDateTime.now(), accessRepository.findById(1).get());

        adminService.addTransaction(List.of(transaction));

        assertEquals(2, adminService.getAllTransaction().size());
    }

    @Test
    @Transactional
    public void addFoodTest() {
        Food food = new Food(new Date(), 6, "rty", transactionRepository.findById(1).get());

        adminService.addFood(List.of(food));

        assertEquals(3, adminService.getAllFood().size());
    }

    @Test
    @Transactional
    public void deleteUsersTest() {
        User user = new User("Den", "French", "Psf", "345",
                "pav45@gmail.com", "DF-345", null, "38095674546", UserType.REGULAR_USER);
        userRepository.save(user);
        int userId = userRepository.findUserByEmail("pav45@gmail.com").get().getUserId();

        adminService.deleteUser(List.of(userId));

        assertTrue(userRepository.findById(userId).isEmpty());
    }

    @Test
    @Transactional
    public void deleteOfficeTest() {
        Office office = new Office("USA", "ppoiu", "rty","fgh", "45g", "Sigma");
        officeRepository.save(office);

        adminService.deleteOffice(List.of(2));

        assertTrue(officeRepository.findById(2).isEmpty());
    }

    @Test
    @Transactional
    public void deleteModelTest() {
        Model model = new Model("AF-45", "LG", 34, 2400);
        modelRepository.save(model);

        adminService.deleteModel(List.of(2));

        assertTrue(modelRepository.findById(2).isEmpty());
    }

    @Test
    @Transactional
    public void deleteFridgeTest() {
        Fridge fridge = new Fridge(4.0f, 20.0f, officeRepository.findById(1).get(), modelRepository.findById(1).get());
        fridgeRepository.save(fridge);

        adminService.deleteFridge(List.of(2));

        assertTrue(fridgeRepository.findById(2).isEmpty());
    }

    @Test
    @Transactional
    public void deleteSubscriptionTest() {
        Subscription subscription = new Subscription(new Date(), new Date(), 456, userRepository.findById(1).get());
        subscriptionRepository.save(subscription);

        adminService.deleteSubscription(List.of(2));

        assertTrue(subscriptionRepository.findById(2).isEmpty());
    }

    @Test
    @Transactional
    public void deleteAccessTest() {
        Access access = new Access(userRepository.findById(1).get(), fridgeRepository.findById(4).get());
        accessRepository.save(access);

        adminService.deleteAccess(List.of(2));

        assertTrue(accessRepository.findById(2).isEmpty());
    }

    @Test
    @Transactional
    public void deleteProductTest() {
        Product product = new Product("Peach", 300f);
        productRepository.save(product);

        adminService.deleteProduct(List.of(2));

        assertTrue(productRepository.findById(2).isEmpty());
    }

    @Test
    @Transactional
    public void deleteOrderTest() {
        AutoOrder autoOrder = new AutoOrder(LocalDateTime.now(), 4, accessRepository.findById(1).get(), productRepository.findById(1).get());
        autoOrderRepository.save(autoOrder);

        adminService.deleteAutoOrder(List.of(2));

        assertTrue(autoOrderRepository.findById(2).isEmpty());
    }

    @Test
    @Transactional
    public void deleteTransactionTest() {
        Transaction transaction = new Transaction(LocalDateTime.now(), LocalDateTime.now(), accessRepository.findById(1).get());
        transactionRepository.save(transaction);

        adminService.deleteTransaction(List.of(2));

        assertTrue(transactionRepository.findById(2).isEmpty());
    }

    @Test
    @Transactional
    public void deleteFoodTest() {
        Food food = new Food(new Date(), 6, "rty", transactionRepository.findById(1).get());
        foodRepository.save(food);

        adminService.deleteFood(List.of(2));

        assertTrue(foodRepository.findById(2).isEmpty());
    }
}
