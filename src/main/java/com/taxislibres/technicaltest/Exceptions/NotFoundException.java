package com.taxislibres.technicaltest.Exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String entity, Long id){
        super("The " + entity + " with id " + id + " doesn't exists.");
    }
}
