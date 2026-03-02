package org.plateau.exception;

public class NoValidConstructorException extends RuntimeException{
    public NoValidConstructorException(String message){
        super(message);
    }
}
