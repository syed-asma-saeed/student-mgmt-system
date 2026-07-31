package com.sms.exceptions;

public class StudentNotFoundException extends Exception{
    StudentNotFoundException(String message){
        super(message);
    }
}