package com.system.fridges.models.transferObjects.userObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
public class UserFood {

    @JsonProperty("name")
    private String name;

    @JsonProperty("number_boxes")
    private int numberBoxes;

    @JsonProperty("date_delivery")
    private Date dateValidity;

    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @JsonProperty("fridge_access")
    private int fridgeAccess;
}
