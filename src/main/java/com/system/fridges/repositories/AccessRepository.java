package com.system.fridges.repositories;

import com.system.fridges.models.entities.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessRepository extends JpaRepository<Access, Integer> {

    @Query(value = "SELECT * FROM access as ac WHERE ac.user_access = :userId", nativeQuery = true)
    List<Access> findAllAccessForUserById(@Param("userId") int userId);
}
