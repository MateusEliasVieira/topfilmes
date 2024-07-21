package com.ifgoiano.topfilmes.domain.domainException;

public class BusinessRulesException extends RuntimeException{
    public BusinessRulesException(String message){
        super(message);
    }
}
