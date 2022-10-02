package com.project.readingisgood.util;

import org.apache.commons.validator.routines.EmailValidator;

public class EmailUtil {

    private static EmailValidator emailValidator = null;

    private EmailUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static EmailValidator getEmailValidator(){
        if(emailValidator == null){
            emailValidator = EmailValidator.getInstance();
        }
        return emailValidator;
    }

    public static boolean isValid(String email) {
        return getEmailValidator().isValid(email);
    }

}
