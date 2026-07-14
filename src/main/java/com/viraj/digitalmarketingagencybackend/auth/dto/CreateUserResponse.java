package com.viraj.digitalmarketingagencybackend.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserResponse {

    private UserResponse user;

    private String temporaryPassword;

}