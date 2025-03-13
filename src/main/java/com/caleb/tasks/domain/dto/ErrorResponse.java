package com.caleb.tasks.domain.dto;

//Java Records automatically give you construction, built in Getters, equals() hashcodes(), toString() and is immutable by defaults
public record ErrorResponse(
        int status, //http status code will be returned
        String message, //string message (identify the type of error) will be returned
        String details //string details (gives you the specifics of the error) will be returned
) {
}
