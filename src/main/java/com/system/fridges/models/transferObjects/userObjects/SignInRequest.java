package com.system.fridges.models.transferObjects.userObjects;

import lombok.Data;

@Data
public class SignInRequest {

    private String email;

    private String password;
}
