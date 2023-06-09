package com.slabstech.mailanalyzer;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.slabstech.mailanalyzer.EmailValidation.patternMatches;

@Component
public class MailCountProcessor {

    private static final Serde<String> STRING_SERDE = Serdes.String();
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {

        String whitespaceMetaChar = "\\s";
        KStream<String, String> messageStream = streamsBuilder
                .stream("input-topic", Consumed.with(STRING_SERDE, STRING_SERDE));
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";


        KTable<String, Long> wordCounts =
                messageStream
                .mapValues((ValueMapper<String, String>) String::toLowerCase)
                .flatMapValues(value -> (Arrays.stream(value.split(whitespaceMetaChar)).distinct().filter(val -> patternMatches(val, regexPattern))).toList() )
                .groupBy((key, word) -> word, Grouped.with(STRING_SERDE, STRING_SERDE))
                .count(Materialized.as("counts"));

        wordCounts.toStream().to("output-topic");
    }

}

