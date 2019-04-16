package com.webproject.homework.service.exception;

public class DataIntegrityValidationException extends RuntimeException {

    public DataIntegrityValidationException(String msg) {
        super(msg);
    }
}
