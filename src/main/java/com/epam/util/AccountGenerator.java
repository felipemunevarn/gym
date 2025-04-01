package com.epam.util;

import java.util.UUID;
import java.util.function.Function;

public final class AccountGenerator {

    // Generic username generator (works for Trainee, Trainer, etc.)
    public static String generateUsername(
        String firstName,
        String lastName,
        Function<String, Boolean> usernameExistsChecker // Inject DAO logic via lambda
    ) {
        String base = firstName + "." + lastName;
        String username = base;
        int suffix = 1;
        while (usernameExistsChecker.apply(username)) {
            username = base + suffix;
            suffix++;
        }
        return suffix > 1 ? username : base;
    }

    // Reusable password generator
    public static String generatePassword() {
        return UUID.randomUUID()
            .toString()
            .replace("-", "")
            .substring(0, 10);
    }
}
