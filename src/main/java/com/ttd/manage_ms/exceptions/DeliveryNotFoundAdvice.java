package com.ttd.manage_ms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody

public class DeliveryNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler({DeliveryNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String EntityNotFoundAdvice(DeliveryNotFoundException ex){
        return ex.getMessage();
    }
}
