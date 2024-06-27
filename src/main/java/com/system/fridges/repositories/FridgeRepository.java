package com.system.fridges.repositories;

import com.system.fridges.models.entities.Fridge;
import com.system.fridges.models.transferObjects.fridgeObjects.FridgeSpending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FridgeRepository extends JpaRepository<Fridge, Integer> {

    @Query(name = "FridgeSpendingQuery", nativeQuery = true)
    List<FridgeSpending> getSpendingMoneyForEveryFridge(@Param("priceForElectricity") float priceForElectricity,
                                                        @Param("nameCompany") String nameCompany);

    @Query(value = "SELECT SUM(spendingMoney) AS totalSpendingMoney\n" +
            "FROM (SELECT  m.energy_per_year * :priceForElectricity AS spendingMoney FROM fridge as f\n" +
            "      LEFT JOIN model as m ON f.model_id = m.model_id WHERE :nameCompany IN (SELECT name_company FROM office)\n" +
            "      ) AS t;", nativeQuery = true)
    float getSpendingMoneyAllFridges(@Param("priceForElectricity") float priceForElectricity,
                                     @Param("nameCompany") String nameCompany);

    @Query(value = "SELECT * FROM fridge as f WHERE f.fridge_id IN (\n" +
            "  SELECT ac.fridge_access FROM access as ac WHERE ac.user_access = :userId)  ", nativeQuery = true)
    Optional<List<Fridge>> getFridgesByUserId(@Param("userId") int userId);


    @Query(value = "SELECT fridge_id, temperature, humidity, f.model_id, f.office_id " +
            "FROM fridge as f LEFT JOIN office as o ON f.office_id = o.office_id " +
            "WHERE o.name_company = :nameCompany", nativeQuery = true)
    List<Fridge> getCompanyFridges(@Param("nameCompany") String nameCompany);
}
