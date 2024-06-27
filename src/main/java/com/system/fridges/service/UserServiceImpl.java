package com.system.fridges.service;

import com.system.fridges.models.entities.*;
import com.system.fridges.models.transferObjects.userObjects.UserTransactionHistory;
import com.system.fridges.models.transferObjects.userObjects.UserFood;
import com.system.fridges.models.transferObjects.userObjects.UserOrder;
import com.system.fridges.repositories.*;
import com.system.fridges.service.interfaces.UserService;
import com.system.fridges.service.utils.PhotoParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private FridgeRepository fridgeRepository;

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AutoOrderRepository autoOrderRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public UserServiceImpl() {}

    @Override
    @Transactional
    public List<Fridge> getFridgesByUserEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElse(new User());
        return fridgeRepository.getFridgesByUserId(user.getUserId()).get();
    }

    @Override
    public List<UserFood> getAllFoodUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElse(new User());
        return foodRepository.getAllFoodUserById(user.getUserId());
    }

    @Override
    public List<UserTransactionHistory> getTransactionHistoryByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElse(new User());
        return transactionRepository.getHistoryUsingByUserId(user.getUserId());
    }

    @Override
    public List<UserOrder> getAllOrdersForUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElse(new User());
        List<Subscription> presentSubscription = subscriptionRepository.getActualSubscriptionsForUser(user.getUserId());
        if (!presentSubscription.isEmpty()) {
            return autoOrderRepository.getAllOrdersForUserById(user.getUserId());
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public byte[] getUserPhoto(String email) {
        String photoPath = userRepository.findUserByEmail(email).orElse(new User()).getPhoto();
        return PhotoParser.pullPhoto(photoPath);
    }

    @Override
    public void saveUser(User user, MultipartFile file) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setHashPassword(passwordEncoder.encode(user.getHashPassword()));

        PhotoParser photoParser = new PhotoParser(file);
        photoParser.savePhoto();

        user.setPhoto(photoParser.getAbsolutePath());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String email) {
        User user = userRepository.findUserByEmail(email).orElse(new User());
        userRepository.deleteById(user.getUserId());
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(new User());
    }

    @Override
    public boolean hasActualSubscription(String email) {
        User user = userRepository.findUserByEmail(email).orElse(new User());
        return !subscriptionRepository.getActualSubscriptionsForUser(user.getUserId()).isEmpty();
    }

    public void addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void addAutoOrders(List<AutoOrder> orders) {
        autoOrderRepository.saveAll(orders);
    }

    public void addFood(List<Food> food) {
        foodRepository.saveAll(food);
    }

    public void addSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }
}
