package com.example.apispringjwt.exeption;

import com.example.apispringjwt.dto.send.ResponseExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExeptionHandler {
    @ControllerAdvice
    public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(value = {ResponseEntityException.class})
        public ResponseEntity<ResponseExceptionDTO> exception(ResponseEntityException exception, WebRequest req) {
            // When exception is caught, we return a response with the exception message and status
            return new ResponseEntity<>(
                    new ResponseExceptionDTO(exception.getMessage()),
                    exception.getStatus()
            );
        }

    }
}
