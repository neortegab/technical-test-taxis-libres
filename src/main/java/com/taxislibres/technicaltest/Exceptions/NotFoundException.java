package com.taxislibres.technicaltest.Exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String entity, Long id){
        super("The " + entity + " with id " + id + " doesn't exists.");
    }

    public NotFoundException(String parentEntity, Long parentId, String childEntity, Long childId) {
        super("The " + childEntity + "with id: " + childId + " doesn't exists for the " + parentEntity + " with id: " + parentId);
    }
}
