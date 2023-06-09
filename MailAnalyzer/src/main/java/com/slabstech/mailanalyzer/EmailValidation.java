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

    public static Integer uniqueCounts(String evenStream){

        String whitespaceMetaChar = "\\s";
        //Integer count = Arrays.stream(evenStream.split(whitespaceMetaChar)).map(String::toLowerCase).filter()

        return 0;
    }
}
