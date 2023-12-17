package com.example.demo.handler.response;

import lombok.Data;

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
}
