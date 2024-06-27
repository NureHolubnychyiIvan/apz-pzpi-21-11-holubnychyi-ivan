package com.system.fridges.controllers;

import com.system.fridges.models.entities.User;
import com.system.fridges.models.enam.UserType;
import com.system.fridges.models.transferObjects.authenticationObjects.JwtAuthenticationResponse;
import com.system.fridges.models.transferObjects.authenticationObjects.RefreshTokenRequest;
import com.system.fridges.models.transferObjects.userObjects.SignInRequest;
import com.system.fridges.service.AuthenticationServiceImpl;
import com.system.fridges.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private AuthenticationServiceImpl authenticationService;

    @Test
    void signInReturnsValidResponse() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail("pasakan2@gmail.com");
        signInRequest.setPassword("1234");
        when(authenticationService.signIn(signInRequest)).thenReturn(new JwtAuthenticationResponse());

        ResponseEntity<JwtAuthenticationResponse> responseEntity = authenticationController.singIn(signInRequest);

        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void refreshReturnsValidResponse() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setToken("413F442847284862506553685660597033733676397924422645294848406351");
        when(authenticationService.refreshToken(refreshTokenRequest)).thenReturn(new JwtAuthenticationResponse());

        ResponseEntity<JwtAuthenticationResponse> responseEntity = authenticationController.refresh(refreshTokenRequest);

        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void registerUserSavesCorrectUser() {
        User user = new User( "Pavlo", "Ko", "ggg",
                "5678", "pavlo.kokhanevych@nure.ua", "AD_45",
                "", "345534234", UserType.REGULAR_USER);
        MultipartFile file = mock(MultipartFile.class);

        authenticationController.registerUser(user, file);

        verify(userService, times(1)).saveUser(user, file);
    }
}
