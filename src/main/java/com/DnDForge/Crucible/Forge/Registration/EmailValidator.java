package com.DnDForge.Crucible.Forge.Registration;

import org.springframework.stereotype.Service;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {
    //TODO implement a way to validate emails are real addresses and not just strings or
    // or temporary email addresses
    public static boolean isValid(String email) {
        return true;
    }

    @Override
    public boolean test(String s) {
        // regex pattern for email validation
        String emailRegex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }
}
