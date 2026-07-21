package com.viraj.digitalmarketingagencybackend.auth.exception;

public class UnauthorizedRoleAssignmentException extends RuntimeException{

    public UnauthorizedRoleAssignmentException(String message){
        super(message);
    }
}
