package com.epam.library.validator;

import com.epam.library.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import static com.epam.library.util.ConstantsOfLibrary.*;

public class AuthorizationValidator {
    public static final String MAIL_REGEX = "([.[^@\\s]]+)@([.[^@\\s]]+)\\.([a-z]+)";
    public static final String PASSWORD_REGEX = "[a-zA-Z0-9]{6}";

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
