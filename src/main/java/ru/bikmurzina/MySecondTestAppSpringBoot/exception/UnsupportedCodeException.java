package ru.bikmurzina.MySecondTestAppSpringBoot.exception;

import org.springframework.validation.BindingResult;

public class UnsupportedCodeException extends Exception {

    public UnsupportedCodeException(String message) {
        super(message);

    }

    public static void isValid(BindingResult bindingResult) {
    }
}