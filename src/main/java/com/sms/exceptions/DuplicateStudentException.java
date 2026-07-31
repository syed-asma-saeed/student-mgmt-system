package com.sms.exceptions;

public class DuplicateStudentException extends Exception{
    DuplicateStudentException(String message){
        super(message);
    }
}