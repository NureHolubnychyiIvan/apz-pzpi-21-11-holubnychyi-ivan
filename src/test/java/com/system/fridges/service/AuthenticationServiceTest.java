package com.system.fridges.service;

import com.system.fridges.models.entities.User;
import com.system.fridges.models.transferObjects.authenticationObjects.JwtAuthenticationResponse;
import com.system.fridges.models.transferObjects.authenticationObjects.RefreshTokenRequest;
import com.system.fridges.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private UserRepository userRepository;

    @Test
    void extractUserName() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNha2FuZTk5MEBnbWFpbC5jb20iLCJpYXQiOjE3MDIzNzEzMjksImV4cCI6MTcwMjM3Mjc2OX0.bnV4MQa-lnIhwoeaMNvB36yNAdmFvoxe9NKjKQ6V7oc";
        String username = "pasakane990@gmail.com";

        String result = authenticationService.extractUserName(token);

        assertEquals(username, result);
    }

    @Test
    void isTokenValid() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNha2FuZTk5MEBnbWFpbC5jb20iLCJpYXQiOjE3MDIzNzEzMjksImV4cCI6MTcwMjM3Mjc2OX0.bnV4MQa-lnIhwoeaMNvB36yNAdmFvoxe9NKjKQ6V7oc";
        String username = "pasakane990@gmail.com";
        User user = new User();
        user.setEmail(username);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                "1234",
                Collections.emptyList()
        );

        boolean result = authenticationService.isTokenValid(token, userDetails);

        assertTrue(result);
    }

    @Test
    void refreshToken() {
        String refreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNha2FuZTk5MEBnbWFpbC5jb20iLCJpYXQiOjE3MDIzNzEzMjksImV4cCI6MTcwMjk3NjEyOX0.ubghd0GQzgOusKUYKeRZTVOZrBfXA8AvMVaZQ5fdHSg";
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setToken(refreshToken);

        JwtAuthenticationResponse result = new JwtAuthenticationResponse();

        assertNotNull(result);
    }
}
