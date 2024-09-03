package com.sankalp.shoppingServer.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String messageToDisplay) {

        super(messageToDisplay);
    }
}
