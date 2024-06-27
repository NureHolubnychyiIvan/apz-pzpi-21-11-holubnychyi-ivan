package com.system.fridges.service.interfaces;

import com.system.fridges.models.transferObjects.authenticationObjects.JwtAuthenticationResponse;
import com.system.fridges.models.transferObjects.authenticationObjects.RefreshTokenRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface AuthenticationService {

    String extractUserName(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generationToken(UserDetails userDetails);

    String generationRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshToken);
}
