package com.restsecurity.authservice.payload.request;

import com.restsecurity.authservice.model.RoleEntity;
import com.restsecurity.authservice.model.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private RoleEnum role;
}
