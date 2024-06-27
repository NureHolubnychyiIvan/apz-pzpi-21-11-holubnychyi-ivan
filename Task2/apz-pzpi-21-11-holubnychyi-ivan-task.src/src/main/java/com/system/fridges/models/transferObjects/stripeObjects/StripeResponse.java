package com.system.fridges.models.transferObjects.stripeObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StripeResponse {

    private String intentID;

    private String clientSecret;
}
