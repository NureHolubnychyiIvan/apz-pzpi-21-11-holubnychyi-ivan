package com.system.fridges.models.transferObjects.fridgeObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FridgeSpending{

    @JsonProperty("fridge_id")
    private int fridgeId;

    @JsonProperty("name_model")
    private String nameModel;

    @JsonProperty("energy_per_year")
    private Double energyPerYear;

    @JsonProperty("spendingMoney")
    private Double spendingMoney;
}
