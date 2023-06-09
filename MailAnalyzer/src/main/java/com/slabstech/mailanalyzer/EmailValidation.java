package com.slabstech.mailanalyzer;

import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.ValueMapper;

import java.util.Arrays;
import java.util.regex.Pattern;

public class EmailValidation {

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
            .matcher(emailAddress)
            .matches();
    }

    public static Integer uniqueCounts(String evenStreams){

        String eventStream = "mail@sachin.com sachin@com.mail @scchin sachin@mail";
        String whitespaceMetaChar = "\\s";
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";


        long count = Arrays.stream(eventStream.split(whitespaceMetaChar)).
                map(String::toLowerCase).
                distinct().
                filter( val ->patternMatches(val,regexPattern)).count() ;

        System.out.println(count);

        return 0;
    }
}
