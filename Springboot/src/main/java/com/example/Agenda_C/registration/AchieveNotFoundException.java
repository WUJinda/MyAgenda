package com.example.Agenda_C.registration;

public class AchieveNotFoundException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public AchieveNotFoundException(String message) {
        super(message);
    }
    public AchieveNotFoundException(String message, Exception e) {
        super(message,e);
    }
}