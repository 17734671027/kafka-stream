package com.jcloud.kafka.binds;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class OneBind {
    // One-to-one consumption
    @Bean
    public Function<KStream<Object, String>, KStream<?, String>> processOne() {

        return input -> input
                .flatMapValues(value -> Arrays.asList(value.toLowerCase()))
                ;
    }
    @Bean
    public Consumer<KStream<?, String>> consumerOne() {
        return input -> input.foreach((key, value) -> System.out.println("one Key: " + key + " Value: " + value));
    }

}
