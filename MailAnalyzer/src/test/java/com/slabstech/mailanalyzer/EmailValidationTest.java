package com.slabstech.mailanalyzer;

import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static com.slabstech.mailanalyzer.EmailValidation.patternMatches;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidationTest {

    private String emailAddress;
    private String regexPattern;
    @Test
    public void testUsingRFC5322Regex() {
        emailAddress = "username@domain.com";
        regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$";
        assertTrue(patternMatches(emailAddress, regexPattern));

     //   String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        String whitespaceMetaChar = "\\s";
// sachin@mail
        String eventStream = "mail@sachin.com sachin@com.mail @scchin sachin@mail";
        long count = Arrays.stream(eventStream.split(whitespaceMetaChar)).
                map(String::toLowerCase).
                distinct().
                filter( val ->patternMatches(val,regexPattern)).count() ;

       // eventStream = "username@domain.com";

        System.out.println(count);

        //String eventStream = "mail@sachin.com sachin@com.mail @scchin sachin@mail";

    }
}
