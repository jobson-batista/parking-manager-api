package com.parkingmanager.api.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${app.url.base.dev}")
    private String urlBaseLocal;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> globalException(Exception exception, WebRequest request) {
        exception.printStackTrace();
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getDescription(false),
                "Internal server error",
                exception.getMessage()
        );
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(VehicleNotSavedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> vehicleNotSavedException(VehicleNotSavedException exception, WebRequest request) {
        exception.printStackTrace();
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false),
                exception.getMessage(),
                exception.getDescription()
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParkingInvalidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> parkingNotSavedException(ParkingInvalidException exception, WebRequest request) {
        exception.printStackTrace();
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false),
                exception.getMessage(),
                exception.getDescription()
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
