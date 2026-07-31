package com.sms.exceptions;

public class DuplicateStudentException extends RuntimeException{
    DuplicateStudentException(String message){
        super(message);
    }
}