package com.db.authserver.exception;

public class UserIsAlreadyRegisteredException extends RuntimeException {
    public UserIsAlreadyRegisteredException(String msg) {
        System.out.println(msg);
    }
}
