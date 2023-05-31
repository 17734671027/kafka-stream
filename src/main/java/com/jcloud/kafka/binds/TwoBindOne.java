package com.jcloud.kafka.binds;

import com.jcloud.kafka.pojo.Event;
import jakarta.annotation.Resource;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

@Component
public class TwoBindOne {
    @Resource
    private KeyValueMapper selectKeyKeyValueMapper;
    @Resource
    private ValueMapper stringToEvent;


    //two->Zero
    @Bean
    public BiConsumer<KStream<String, String>, KStream<String, String>> twoToZero() {
        return (input1, input2) -> {
            input1.merge(input2).mapValues(stringToEvent).filter((k, v) -> v != null).selectKey(selectKeyKeyValueMapper).foreach((key, value) -> System.out.println("two Key: " + key + " Value: " + value));
        };
    }
    //two->one
    @Bean
    public BiFunction<KStream<String, String>, KStream<String, String>, KStream<String, Event>[]> twoToOne() {
        return (input1, input2) -> input1.merge(input2).mapValues(stringToEvent).filter((k, v) -> v != null).selectKey(selectKeyKeyValueMapper).branch(((key, value) ->true ));
    }

    //two->multiple
    @Bean
    public BiFunction<KStream<String, String>, KStream<String, String>, KStream<String, Event>[]> twoToMultiple() {
        List<Predicate<String, Event>> predicates = Arrays.asList(
                (k, v) -> v.getSource().equals("online"),
                (k, v) -> v.getSource().equals("Inside"),
                (k, v) -> v.getSource().equals("ThirdParty"));
        return (input1, input2) -> input1.merge(input2).mapValues(stringToEvent).filter((k, v) -> v != null).selectKey(selectKeyKeyValueMapper).branch(predicates.toArray(new Predicate[predicates.size()]));
    }


}
