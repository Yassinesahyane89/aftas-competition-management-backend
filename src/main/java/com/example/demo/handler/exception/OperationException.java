package com.example.demo.handler.exception;

public class OperationException extends RuntimeException{
    public OperationException(String message){
        super(message);
    }
}
