package com.resturant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public InvalidRequestException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s has invalid %s: '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}

