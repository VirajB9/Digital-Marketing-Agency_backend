package com.viraj.digitalmarketingagencybackend.auth.dto;

import com.viraj.digitalmarketingagencybackend.auth.enmus.UserStatus;
import com.viraj.digitalmarketingagencybackend.auth.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
public class UserResponse {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String roleName;

    private UserStatus status;

}
