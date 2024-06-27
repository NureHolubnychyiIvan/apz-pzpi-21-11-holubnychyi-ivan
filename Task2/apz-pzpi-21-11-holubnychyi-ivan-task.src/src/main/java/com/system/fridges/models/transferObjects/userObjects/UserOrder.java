package com.system.fridges.models.transferObjects.userObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserOrder {

    @JsonProperty("date_delivery")
    private LocalDateTime dateDelivery;

    @JsonProperty("number")
    private int number;

    @JsonProperty("fridge_access")
    private int fridgeAccess;

    @JsonProperty("name")
    private String name;

    @JsonProperty("weight")
    private double weight;
}
