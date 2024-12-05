package com.abdo.hunter.exception.exps;

public   class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }

}
