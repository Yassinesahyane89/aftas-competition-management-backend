package com.example.demo.handler.response;

import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class ResponseMessage {
    private String message;
    private Object data;

    public ResponseMessage(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseMessage(String message) {
        this.message = message;
    }

    // ok
    public static ResponseEntity<?> ok(Object data, String message) {
        return ResponseEntity.ok(new ResponseMessage(message, data));
    }

    // created
    public static ResponseEntity<?> created(Object data, String message) {
        return ResponseEntity.ok(new ResponseMessage(message, data));
    }

    // bad request
    public static ResponseEntity<?> badRequest(String message) {
        return ResponseEntity.badRequest().body(new ResponseMessage(message));
    }
}
