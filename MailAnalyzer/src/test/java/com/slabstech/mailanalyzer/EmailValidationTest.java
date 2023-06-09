package com.slabstech.mailanalyzer;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidationTest {

    private String emailAddress;
    private String regexPattern;
    @Test
    public void testUsingRFC5322Regex() {
        emailAddress = "username@domain.com";
        regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        assertTrue(EmailValidation.patternMatches(emailAddress, regexPattern));


        String eventStream = "mail@sachin.com sachin@com.mail @scchin sachin@mail";

    }
}
