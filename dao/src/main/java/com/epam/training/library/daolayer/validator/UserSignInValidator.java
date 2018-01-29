package com.epam.training.library.daolayer.validator;

import com.epam.training.library.daolayer.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSignInValidator implements Verifiable<User> {
    private static final Logger Log = LogManager.getLogger(UserSignInValidator.class.getSimpleName());
    private static final String EMAIL_PATTERN = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]+$";
    private static final String PASSWORD_PATTERN = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,40})$";


    @Override
    public boolean validate(User user) {
        String email = user.getEmail();
        boolean isEmailValid = isMatches(EMAIL_PATTERN, email);
        Log.info("Is email valid : " + email + " = " + isEmailValid);
        String password = user.getPassword();
        boolean isPasswordValid = isMatches(PASSWORD_PATTERN, password);
        Log.info("Is password valid : " + password + " = " + isPasswordValid);
        return isEmailValid && isPasswordValid;
    }

    private boolean isMatches(String patternRegex, String testable) {
        Pattern pattern = Pattern.compile(patternRegex);
        Matcher matcher = pattern.matcher(testable);
        boolean isValid = matcher.matches();

        return isValid;
    }
}
