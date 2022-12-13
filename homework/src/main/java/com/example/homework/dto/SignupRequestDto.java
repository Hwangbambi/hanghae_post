package com.example.homework.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Setter
@Getter
public class SignupRequestDto {
    @Pattern(regexp = "[a-z0-9]{4,11}")
    private String username;
    @Pattern(regexp = "[a-zA-Z0-9]{8,16}")
    private String password;
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}