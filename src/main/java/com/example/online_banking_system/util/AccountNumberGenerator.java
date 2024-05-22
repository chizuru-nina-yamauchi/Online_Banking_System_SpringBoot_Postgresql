package com.example.online_banking_system.util;

import java.util.Random;

public class AccountNumberGenerator {
    private static final int ACCOUNT_NUMBER_LENGTH = 12;

public static String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            int digit = random.nextInt(10);
            accountNumber.append(digit);
        }
        return accountNumber.toString();
    }

}
