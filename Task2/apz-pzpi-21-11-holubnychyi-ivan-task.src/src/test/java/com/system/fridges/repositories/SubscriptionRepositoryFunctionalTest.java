package com.system.fridges.repositories;

import com.system.fridges.models.entities.Subscription;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubscriptionRepositoryFunctionalTest {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Test
    public void testGetActualSubscriptionsForUser() {
        int userId = 1;

        List<Subscription> actualSubscriptions = subscriptionRepository.getActualSubscriptionsForUser(userId);

        assertNotNull(actualSubscriptions);
    }
}
