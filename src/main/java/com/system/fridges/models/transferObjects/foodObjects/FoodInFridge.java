package com.system.fridges.models.transferObjects.foodObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
public class FoodInFridge {

    @JsonProperty("name")
    private String name;

    @JsonProperty("number_boxes")
    private int numberBoxes;

    @JsonProperty("date_validity")
    private Date dateValidity;

    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("patronymic")
    private String patronymic;

    @JsonProperty("email")
    private String email;
}

