package com.viraj.digitalmarketingagencybackend.auth.exception;

public class DuplicatePhoneException extends RuntimeException {

    public DuplicatePhoneException(String phoneNumber) {
        super("Phone number already exists: " + phoneNumber);
    }
}
