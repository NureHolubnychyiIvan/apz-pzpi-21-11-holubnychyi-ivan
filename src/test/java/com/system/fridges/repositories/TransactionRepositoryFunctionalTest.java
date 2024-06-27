package com.system.fridges.repositories;


import com.system.fridges.models.transferObjects.fridgeObjects.FridgeTransactionHistory;
import com.system.fridges.models.transferObjects.userObjects.UserTransactionHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionRepositoryFunctionalTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    @Transactional
    public void getHistoryUsingByUserIdTest() {
        int userId = 1;

        List<UserTransactionHistory> userTransactionHistoryList = transactionRepository.getHistoryUsingByUserId(userId);

        assertNotNull(userTransactionHistoryList);
    }

    @Test
    @Transactional
    public void getHistoryUsingFridgeTest() {
        int fridgeId = 1;

        List<FridgeTransactionHistory> fridgeTransactionHistoryList = transactionRepository.getHistoryUsingFridge(fridgeId);

        assertNotNull(fridgeTransactionHistoryList);
    }
}
