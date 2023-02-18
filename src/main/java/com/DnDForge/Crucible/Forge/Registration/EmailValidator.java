package com.DnDForge.Crucible.Forge.Registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    public static boolean isValid(String email) {
        return true;
    }

// implement regex to validate email
    @Override
    public boolean test(String s) {
        return true;
    }
}
