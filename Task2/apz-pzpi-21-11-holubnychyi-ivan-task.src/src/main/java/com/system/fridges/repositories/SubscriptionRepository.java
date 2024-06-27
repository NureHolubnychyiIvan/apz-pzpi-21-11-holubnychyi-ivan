package com.system.fridges.repositories;

import com.system.fridges.models.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    @Query(value = "SELECT * FROM subscription as s " +
            "WHERE s.user_id = :userId AND end_date > current_date() ", nativeQuery = true)
    List<Subscription> getActualSubscriptionsForUser(@Param("userId") int userId);
}
