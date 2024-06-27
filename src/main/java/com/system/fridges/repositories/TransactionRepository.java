package com.system.fridges.repositories;

import com.system.fridges.models.entities.Transaction;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeTransactionHistory;
import com.system.fridges.models.transferObjects.userObjects.UserTransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query(name = "UserTransactionHistoryQuery", nativeQuery = true)
    List<UserTransactionHistory> getHistoryUsingByUserId(@Param("userId") int userId);

    @Query(nativeQuery = true, name = "FridgeTransactionHistoryQuery")
    List<FridgeTransactionHistory> getHistoryUsingFridge(@Param("fridgeId") int fridgeId);
}
