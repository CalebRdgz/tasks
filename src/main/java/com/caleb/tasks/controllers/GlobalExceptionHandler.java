package com.caleb.tasks.controllers;

import com.caleb.tasks.domain.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice //Tells Spring that this class handles exceptions across all of our controllers
public class GlobalExceptionHandler {

    //add a method to handle those exceptions:
    @ExceptionHandler({IllegalArgumentException.class}) //specify the illegalArgumentException that we're throwing in our application
    public ResponseEntity<ErrorResponse> handleExceptions( //public method will return a "ResponseEntity" of type <ErrorResponse> called "handleExceptions"
            RuntimeException ex, WebRequest request //two arguments: RuntimeExceptions called "ex" and WebRequest called "request"
    ) {
        //Create a new ErrorResponse:
        ErrorResponse errorResponse = new ErrorResponse(
            //for the illegalArgument exceptions, we want to return the 400 HTTP status code (bad request)
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        //return the new response entity:
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    //The illegalArgumentException would propogate up to Spring's exception handling mechanism, Spring finds our controller advice class, then matches the exception type (IllegalArgumentException)
    //then our Handler method creates an error response with HTTP status of 400 (bad request), the exception message, then request details. Spring converts the error response to JSON and sends to client.
    //Used a ResponseEntity<> to specify the status code of that response.
}
