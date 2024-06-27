package com.system.fridges.repositories;

import com.system.fridges.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user as u WHERE u.email = :email", nativeQuery = true)
    Optional<User> findUserByEmail(@Param("email") String email);
}
