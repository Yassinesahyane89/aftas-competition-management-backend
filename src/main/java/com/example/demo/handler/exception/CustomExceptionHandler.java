package com.example.demo.handler.exception;

import com.example.demo.handler.response.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(OperationException.class)
    public ResponseEntity<ResponseMessage> handleOperationException(OperationException e){
        ResponseMessage responseMessage = new ResponseMessage(e.getMessage());
        return ResponseEntity.badRequest().body(responseMessage);
    }
}
