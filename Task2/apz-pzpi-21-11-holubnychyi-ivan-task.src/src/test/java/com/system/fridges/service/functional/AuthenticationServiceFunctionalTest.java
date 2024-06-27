package com.system.fridges.service.functional;


import com.system.fridges.models.transferObjects.authenticationObjects.JwtAuthenticationResponse;
import com.system.fridges.models.transferObjects.authenticationObjects.RefreshTokenRequest;
import com.system.fridges.service.AuthenticationServiceImpl;
import com.system.fridges.service.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationServiceFunctionalTest {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void extractUserNameReturnsEmail() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNha2FuZTk5MEBnbWFpbC5jb20iLCJpYXQiOjE3MDIzNzEzMjksImV4cCI6MTcwMjM3Mjc2OX0.bnV4MQa-lnIhwoeaMNvB36yNAdmFvoxe9NKjKQ6V7oc";
        String username = "pasakane990@gmail.com";

        String result = authenticationService.extractUserName(token);

        assertEquals(username, result);
    }

    @Test
    void refreshTokenTest() {
        String refreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNha2FuZTk5MEBnbWFpbC5jb20iLCJpYXQiOjE3MDIzNzEzMjksImV4cCI6MTcwMjk3NjEyOX0.ubghd0GQzgOusKUYKeRZTVOZrBfXA8AvMVaZQ5fdHSg";
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setToken(refreshToken);

        JwtAuthenticationResponse result = authenticationService.refreshToken(refreshTokenRequest);

        assertNotNull(result);
    }

    @Test
    void isTokenValidTest() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNha2FuZTk5MEBnbWFpbC5jb20iLCJpYXQiOjE3MDIzNzEzMjksImV4cCI6MTcwMjM3Mjc2OX0.bnV4MQa-lnIhwoeaMNvB36yNAdmFvoxe9NKjKQ6V7oc";
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "pasakane990@gmail.com",
                "1234",
                Collections.emptyList()
        );

        boolean result = authenticationService.isTokenValid(token, userDetails);

        assertTrue(result);
    }
}
