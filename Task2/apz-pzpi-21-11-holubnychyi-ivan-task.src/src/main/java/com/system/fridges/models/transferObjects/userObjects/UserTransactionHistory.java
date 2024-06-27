package com.system.fridges.models.transferObjects.userObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserTransactionHistory {

    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @JsonProperty("fridge_access")
    private int fridgeAccess;
}
