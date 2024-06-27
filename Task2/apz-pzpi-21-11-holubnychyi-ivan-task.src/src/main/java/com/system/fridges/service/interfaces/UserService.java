package com.system.fridges.service.interfaces;

import com.system.fridges.models.entities.Fridge;
import com.system.fridges.models.entities.User;
import com.system.fridges.models.transferObjects.userObjects.UserTransactionHistory;
import com.system.fridges.models.transferObjects.userObjects.UserFood;
import com.system.fridges.models.transferObjects.userObjects.UserOrder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Integer userId);

    void saveUser(User user, MultipartFile file);

    void deleteUser(String userName);

    List<Fridge> getFridgesByUserEmail(String email);

    List<UserFood> getAllFoodUserByEmail(String email);

    List<UserTransactionHistory> getTransactionHistoryByEmail(String email);

    List<UserOrder> getAllOrdersForUserByEmail(String email);

     User findUserByEmail(String email);

     boolean hasActualSubscription(String email);
}
