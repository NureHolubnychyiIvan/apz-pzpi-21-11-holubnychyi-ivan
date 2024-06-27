package com.system.fridges.repositories;

import com.system.fridges.models.entities.Access;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccessRepositoryFunctionalTest {

    @Autowired
    private AccessRepository accessRepository;

    @Test
    @Transactional
    public void findAllAccessForUserByIdTest() {
        int userId = 1;

        List<Access> accessList = accessRepository.findAllAccessForUserById(userId);

        assertNotNull(accessList);
    }

    @Test
    @Transactional
    public void testFindAllAccessForUserByIdWithNonExistingUser() {
        int nonExistingUserId = -1;

        List<Access> accessList = accessRepository.findAllAccessForUserById(nonExistingUserId);

        assertNotNull(accessList);
        assertEquals(0, accessList.size(), "For a non-existing user, the list should be empty");
    }
}
