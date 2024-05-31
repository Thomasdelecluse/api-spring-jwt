package com.example.apispringjwt.exeption;

import org.springframework.http.HttpStatus;

public class ResponseEntityException extends RuntimeException{
        private final HttpStatus status;
        private final String message;

        public ResponseEntityException(HttpStatus status, String message, Object... args) {
            super(String.format(message, args));
            this.status = status;
            this.message = String.format(message, args);
        }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
