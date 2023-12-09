package com.springboot.blogApp.exception;

public class ResourceNotFound extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private int fieldValue;
    public ResourceNotFound(String resourceName, String fieldName, int fieldValue)
    {
        super(String.format("Resource Name %s , Field Name : %s , %d not found in database"));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
