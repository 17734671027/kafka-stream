package com.demo.kafka.binds;

import com.demo.kafka.pojo.Event;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ConsumerHander {
    @Bean
    public Consumer<KStream<?, Event>> commonCousumer1() {
        return input -> input.foreach((key, value) -> System.out.println("consumer common one Key: " + key + " Value: " + value));
    }

    @Bean
    public Consumer<KStream<?, Event>> commonCousumer2() {
        return input -> input.foreach((key, value) -> System.out.println("consumer common two Key: " + key + " Value: " + value));
    }

    @Bean
    public Consumer<KStream<?, Event>> commonCousumer3() {
        return input -> input.foreach((key, value) -> System.out.println("consumer common three Key: " + key + " Value: " + value));
    }
}
