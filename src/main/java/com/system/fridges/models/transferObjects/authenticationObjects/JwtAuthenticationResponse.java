package com.system.fridges.models.transferObjects.authenticationObjects;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String token;

    private String refreshToken;
}
