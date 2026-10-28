package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String recurso, Long id) {
        super(recurso + " con id " + id + " no existe");
    }
}
