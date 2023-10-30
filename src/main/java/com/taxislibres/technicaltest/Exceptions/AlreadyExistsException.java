package com.taxislibres.technicaltest.Exceptions;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(){
        super("Entity already exists.");
    }
    public AlreadyExistsException(String message){
        super(message);
    }
}
