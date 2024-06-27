package com.system.fridges.repositories;


import com.system.fridges.models.entities.Fridge;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeSpending;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FridgeRepositoryFunctionalTest {

    @Autowired
    private FridgeRepository fridgeRepository;

    @Test
    @Transactional
    public void spendingMoneyForEveryFridgeTest() {
        float priceForElectricity = 0.15f;
        String nameCompany = "Epam";

        List<FridgeSpending> fridgeSpendingList = fridgeRepository.getSpendingMoneyForEveryFridge(priceForElectricity, nameCompany);

        assertNotNull(fridgeSpendingList);
    }

    @Test
    public void spendingMoneyAllFridgesTest() {
        float priceForElectricity = 0.15f;
        String nameCompany = "Epam";

        float totalSpendingMoney = fridgeRepository.getSpendingMoneyAllFridges(priceForElectricity, nameCompany);

        assertNotNull(totalSpendingMoney);
    }

    @Test
    @Transactional
    public void getFridgesByUserIdTest() {
        int userId = 1;

        List<Fridge> fridges = fridgeRepository.getFridgesByUserId(userId).get();

        assertNotNull(fridges);
    }

    @Test
    @Transactional
    public void getCompanyFridgesTest() {
        String nameCompany = "Epam";

        List<Fridge> companyFridges = fridgeRepository.getCompanyFridges(nameCompany);

        assertNotNull(companyFridges);
    }
}
