package com.viraj.digitalmarketingagencybackend.auth.exception;

import com.viraj.digitalmarketingagencybackend.auth.enmus.UserStatus;

public class InvalidUserStatusException extends RuntimeException {

    public InvalidUserStatusException(UserStatus status) {
        super("Status '" + status + "' is not allowed for this operation.");
    }
}