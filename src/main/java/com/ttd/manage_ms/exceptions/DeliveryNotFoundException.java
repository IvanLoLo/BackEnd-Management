package com.ttd.manage_ms.exceptions;

public class DeliveryNotFoundException extends RuntimeException{
    public DeliveryNotFoundException(String message){
        super(message);
    }
}
