package com.system.fridges.controllers.functional;

import com.system.fridges.models.transferObjects.authenticationObjects.JwtAuthenticationResponse;
import com.system.fridges.models.transferObjects.authenticationObjects.RefreshTokenRequest;
import com.system.fridges.models.transferObjects.userObjects.SignInRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationControllerFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void signInCorrectAuthentication() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail("pasakane990@gmail.com");
        signInRequest.setPassword("1234");

        ResponseEntity<JwtAuthenticationResponse> responseEntity = restTemplate.postForEntity("/authentication/signIn", signInRequest, JwtAuthenticationResponse.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void refreshGetException() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setToken("413F442847284862506553685660597033733676397924422645294848406351");

        ResponseEntity<JwtAuthenticationResponse> responseEntity = restTemplate.postForEntity("/authentication/refresh", refreshTokenRequest, JwtAuthenticationResponse.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
