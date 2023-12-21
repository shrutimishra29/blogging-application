package com.application.blog.velvetvoices.exceptions;
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;

    private String fieldName;


    private long fieldValue;

    private String message;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super();
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.message = String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue);
    }

    public ResourceNotFoundException(String message) {
        this.message = message;
    }
}
