package com.viraj.digitalmarketingagencybackend.auth.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userId) {
        super("User not found with id: " + userId);
    }

}