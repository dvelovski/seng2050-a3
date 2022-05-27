package org.a3.services;

import java.util.concurrent.ThreadLocalRandom;

public class PasswordGenerator {
    private static PasswordGenerator instance;
    private static String acceptableChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmopqrstuvwxyz1234567890~!@#$%^&*_";


    public static PasswordGenerator get() {
        if (instance == null) {
            instance = new PasswordGenerator();
        }
        return instance;
    }

    public String generatePassword(int pwdLength){
        ThreadLocalRandom tlRandom = ThreadLocalRandom.current();
        StringBuilder passBuilder = new StringBuilder();

        for (int i = 0; i < pwdLength; i++){
            char c = acceptableChars.charAt(tlRandom.nextInt(acceptableChars.length()));
            passBuilder.append(c);
        }

        return passBuilder.toString();
    }
}
