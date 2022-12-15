package com.lamine.gest_users.exceptions;

public class MysqlUnreachableException extends Exception {
    public MysqlUnreachableException(String message) {
        super(message);
    }

    public MysqlUnreachableException() {
        super("Echec de connection à la base de données");
    }
}
