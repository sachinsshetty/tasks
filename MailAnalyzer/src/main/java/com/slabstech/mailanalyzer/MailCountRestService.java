package com.slabstech.mailanalyzer;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MailCountRestService {

    private final StreamsBuilderFactoryBean factoryBean;

    private final KafkaProducer kafkaProducer;


    @GetMapping("/count/{word}")
    public Long getWordCount(@PathVariable String word) {
        KafkaStreams kafkaStreams =  factoryBean.getKafkaStreams();
        ReadOnlyKeyValueStore<String, Long> counts = kafkaStreams
                .store(StoreQueryParameters.fromNameAndType("counts", QueryableStoreTypes.keyValueStore()));
        return counts.get(word);
    }

    @GetMapping("/words/")
    public ResponseEntity<Map<Integer, String>> getWord() {
        KafkaStreams kafkaStreams =  factoryBean.getKafkaStreams();
        //assert kafkaStreams != null;
        ReadOnlyKeyValueStore<String, Long> counts = kafkaStreams
                .store(StoreQueryParameters.fromNameAndType("counts", QueryableStoreTypes.keyValueStore()));

        Map results = new HashMap<>();
        KeyValueIterator sbs= counts.all();
        while(sbs.hasNext()){
            final KeyValue<String, Long> next = (KeyValue<String, Long>) sbs.next();
            results.put(next.key, next.value);
        }

        if(results.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Map<Integer,String>>(results, HttpStatus.OK);
    }

    @PostMapping("/message")
    public void addMessage(@RequestBody String message) {
        kafkaProducer.sendMessage(message);
    }
}
