package com.system.fridges.repositories;

import com.system.fridges.models.entities.AutoOrder;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeOrder;
import com.system.fridges.models.transferObjects.userObjects.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoOrderRepository extends JpaRepository<AutoOrder, Integer> {

    @Query(nativeQuery = true, name = "FridgeOrderQuery")
    List<FridgeOrder> getInfoOrdersForFridgeById(@Param("fridgeId") int fridgeId);

    @Query(name = "UserOrderQuery", nativeQuery = true)
    List<UserOrder> getAllOrdersForUserById(@Param("userId") int userId);
}
