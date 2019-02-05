package com.epam.library.validator;

import com.epam.library.entity.User;

import static com.epam.library.util.ConstantsOfLibrary.*;

public class AuthorizationValidator {
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

    public static boolean validatePassword(String password, String passFromDB){
        boolean isCorrect;
        isCorrect = passFromDB.equals(password);
        return isCorrect;
    }

    public static boolean validateBlock(User user){
        String block;
        boolean isBlock = false;
        block = user.getBlock();
        if(block.equals("n")){
            isBlock = true;
        }
        return isBlock;
    }
}
