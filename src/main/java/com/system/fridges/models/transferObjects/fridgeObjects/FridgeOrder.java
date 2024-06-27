package com.system.fridges.models.transferObjects.fridgeObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class FridgeOrder {

    @JsonProperty("order_id")
    private int orderId;

    @JsonProperty("date_delivery")
    private LocalDateTime dateDelivery;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("patronymic")
    private String patronymic;

    @JsonProperty("email")
    private String email;
}
