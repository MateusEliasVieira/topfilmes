package com.ifgoiano.demo.domain.domainException;

public class BusinessRulesException extends RuntimeException{
    public BusinessRulesException(String message){
        super(message);
    }
}
