package com.system.fridges.service;


import com.system.fridges.models.entities.Access;
import com.system.fridges.models.entities.AutoOrder;
import com.system.fridges.models.entities.Fridge;
import com.system.fridges.models.entities.User;
import com.system.fridges.models.transferObjects.foodObjects.FoodInFridge;
import com.system.fridges.models.transferObjects.foodObjects.SpoiledFood;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeOrder;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeTransactionHistory;
import com.system.fridges.models.transferObjects.orderObjects.AutoOrderRequest;
import com.system.fridges.repositories.*;
import com.system.fridges.service.interfaces.FridgeService;
import com.system.fridges.service.utils.Delivety;
import com.system.fridges.service.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class FridgeServiceImpl implements FridgeService {

    @Autowired
    private FridgeRepository fridgeRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private AutoOrderRepository autoOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccessRepository accessRepository;

    private final EmailSender emailSender = new EmailSender();

    @Override
    public User checkUserById(int fridgeId, int userId) {
        List<Access> accesses = accessRepository.findAllAccessForUserById(userId);
        User user = userRepository.findById(userId).get();

        if (userRepository.findById(userId).isPresent() && !accesses.isEmpty()) {
            for (Access access : accesses) {
                if (access.getFridge().getFridgeId() == fridgeId &&
                    access.getUser().getUserId() == userId) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public Fridge getFridgeById(int fridgeId) {
       return  fridgeRepository.findById(fridgeId).orElse(null);
    }

    @Override
    public void saveFridge(Fridge fridge) {
        fridgeRepository.save(fridge);
    }

    @Override
    public void deleteFridgeById(int fridgeId) {
        fridgeRepository.deleteById(fridgeId);
    }

    @Override
    public List<FoodInFridge> getFoodInFridgeById(int fridgeId) {
        return foodRepository.getAllFoodForFridge(fridgeId);
    }

    @Override
    public List<FridgeTransactionHistory> getTransactionHistoryById(int fridgeId) {
        return transactionRepository.getHistoryUsingFridge(fridgeId);
    }

    @Override
    public List<FridgeOrder> getAutoOrdersById(int fridgeId, String email) {
        User user = userRepository.findUserByEmail(email).orElse(null);

        if (user != null &&
                !subscriptionRepository.getActualSubscriptionsForUser(user.getUserId()).isEmpty()) {
            return autoOrderRepository.getInfoOrdersForFridgeById(fridgeId);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void doInventoryForFridge(Integer fridgeId) {
        Fridge fridge = fridgeRepository.findById(fridgeId).get();
        List<SpoiledFood> spoiledFoodsInFridge = foodRepository.getSpoiledFoodByFridgeId(fridge.getFridgeId());
        sendEmailEveryOwnerFood(spoiledFoodsInFridge);
    }

    private void sendEmailEveryOwnerFood(List<SpoiledFood> spoiledFoodsInFridge) {
        User user;
        String bodyMessage;

        try {
            for (SpoiledFood spoiledFood : spoiledFoodsInFridge) {
                user = userRepository.findById(spoiledFood.getUserAccess()).get();
                bodyMessage = generateMessageForSpoiledFood(spoiledFood);
                emailSender.sendEmail(user.getEmail(), bodyMessage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String generateMessageForSpoiledFood(SpoiledFood spoiledFood) {
        return "Your food:" + spoiledFood.getName() + ", Date validity:" +
                spoiledFood.getDateValidity() + ", amount: " + spoiledFood.getNumberBoxes() +
                ", date transaction:" + spoiledFood.getEndDate() + " is spoiled. Please, get rid of this food";
    }

    @Override
    public void doAutoOrdering(int fridgeId) {
        List<AutoOrder> autoOrdering = autoOrderRepository.findAll();
        Delivety delivety = new Delivety();
        boolean isTimeToOrder;

        for (AutoOrder autoOrder : autoOrdering) {
            isTimeToOrder = autoOrder.getAccess().getFridge().getFridgeId() == fridgeId &&
                    autoOrder.getDateDelivery().isBefore(LocalDateTime.now()) ||
                    autoOrder.getDateDelivery().isEqual(LocalDateTime.now());

            if (isTimeToOrder) {
                AutoOrderRequest newRequest = new AutoOrderRequest(autoOrder);
                delivety.sendAutoOrdering(newRequest);
                autoOrderRepository.deleteById(autoOrder.getOrderId());
            }
        }
    }
}
