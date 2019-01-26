package com.epam.library.validator;

import com.epam.library.entity.User;

import static com.epam.library.util.ConstantsOfLibrary.*;

public class Validator {
    public static boolean validateMailRegex(String login){
        boolean isCorrect = false;
        if(login.matches(MAIL_REGEX)){
            isCorrect = true;
        }

        return isCorrect;
    }

    public static boolean validatePasswordRegex(String password){
        boolean isCorrect = false;
        if(password.matches(PASSWORD_REGEX)){
            isCorrect = true;
        }
        return isCorrect;
    }

    public static boolean validateUser(User user, String login, String password){
        boolean isCorrect = false;
        if(user.getMail().equals(login) && user.getPassword().equals(password)){
            isCorrect = true;
        }
        return isCorrect;
    }
}
