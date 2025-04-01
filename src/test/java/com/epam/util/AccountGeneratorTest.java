package com.epam.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

class AccountGeneratorTest {

    @Test
    void testGenerateUsernameWhenBaseUsernameDoesNotExist() {
        Function<String, Boolean> usernameExistsChecker = mock(Function.class);
        when(usernameExistsChecker.apply("john.doe")).thenReturn(false);

        String username = AccountGenerator.generateUsername("john", "doe", usernameExistsChecker);

        assertEquals("john.doe", username);
        verify(usernameExistsChecker).apply("john.doe");
    }

    @Test
    void testGenerateUsernameWhenBaseUsernameExists() {
        Function<String, Boolean> usernameExistsChecker = mock(Function.class);
        when(usernameExistsChecker.apply("john.doe")).thenReturn(true);
        when(usernameExistsChecker.apply("john.doe1")).thenReturn(false);

        String username = AccountGenerator.generateUsername("john", "doe", usernameExistsChecker);

        assertEquals("john.doe1", username);
        verify(usernameExistsChecker).apply("john.doe");
        verify(usernameExistsChecker).apply("john.doe1");
    }

    @Test
    void testGenerateUsernameWhenMultipleUsernamesExist() {
        Function<String, Boolean> usernameExistsChecker = mock(Function.class);
        when(usernameExistsChecker.apply("john.doe")).thenReturn(true);
        when(usernameExistsChecker.apply("john.doe1")).thenReturn(true);
        when(usernameExistsChecker.apply("john.doe2")).thenReturn(false);

        String username = AccountGenerator.generateUsername("john", "doe", usernameExistsChecker);

        assertEquals("john.doe2", username);
        verify(usernameExistsChecker).apply("john.doe");
        verify(usernameExistsChecker).apply("john.doe1");
        verify(usernameExistsChecker).apply("john.doe2");
    }

    @Test
    void testGeneratePassword() {
        String password = AccountGenerator.generatePassword();

        assertNotNull(password);
        assertEquals(10, password.length());
    }
}