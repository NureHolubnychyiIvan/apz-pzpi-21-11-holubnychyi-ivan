package com.system.fridges.service.utils;

import com.system.fridges.models.entities.AutoOrder;
import com.system.fridges.models.transferObjects.orderObjects.AutoOrderRequest;
import com.system.fridges.repositories.AutoOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DelivetyFunctionalTest {

    private Delivety delivety = new Delivety();

    @Autowired
    private AutoOrderRepository autoOrderRepository;

    @Test
    public void testDoAutoOrdering() {
        AutoOrder autoOrder = autoOrderRepository.findById(8).get();
        AutoOrderRequest autoOrderRequest = new AutoOrderRequest(autoOrder);

        delivety.sendAutoOrdering(autoOrderRequest);
    }
}
