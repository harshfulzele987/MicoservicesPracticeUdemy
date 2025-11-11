package com.eazybytes.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName, String parameter , String value)
    {
        super(String.format(resourceName + "is not available for parameter "+ parameter + " passing value="+value));
    }
}
