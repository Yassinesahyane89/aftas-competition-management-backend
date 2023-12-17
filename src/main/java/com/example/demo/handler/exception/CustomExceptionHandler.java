package com.example.demo.handler.exception;

import com.example.demo.handler.response.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(OperationException.class)
    public ResponseEntity<ResponseMessage> handleOperationException(OperationException e){
        ResponseMessage responseMessage = new ResponseMessage(e.getMessage());
        return ResponseEntity.badRequest().body(responseMessage);
    }

    @ExceptionHandler(ResourceNotFountException.class)
    public ResponseEntity<ResponseMessage> handleResourceNotFoundException(ResourceNotFountException e){
        ResponseMessage responseMessage = new ResponseMessage(e.getMessage());
        return ResponseEntity.badRequest().body(responseMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException e){
        Map<String, List<String>> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(FieldError::getField, Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));

        return ResponseEntity.badRequest().body(errors);
    }

}
