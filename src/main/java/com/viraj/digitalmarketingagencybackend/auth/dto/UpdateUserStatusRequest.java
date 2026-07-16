package com.viraj.digitalmarketingagencybackend.auth.dto;

import com.viraj.digitalmarketingagencybackend.auth.enmus.UserStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserStatusRequest {

    @NotNull
    private UserStatus status;

}