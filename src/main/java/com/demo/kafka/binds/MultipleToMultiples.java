package com.demo.kafka.binds;

import com.demo.kafka.pojo.Event;
import jakarta.annotation.Resource;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author xuyong96
 */
@Component
public class MultipleToMultiples {

    @Resource
    private KeyValueMapper selectKeyKeyValueMapper;
    @Resource
    private ValueMapper stringToEvent;
    @Resource
    private ValueMapper kafkaValueMapper;

    @Bean
    public Function<KStream<String, String>[], KStream<String, Event>[]> multipleToMultiple() {
        return  inputs->{
            KStream<String, String> mergedStream = inputs[0];

            for (int i = 1; i <inputs.length ; i++) {
                mergedStream=mergedStream.merge(inputs[i]);
            }
            List<Predicate<String, Event>> predicates = Arrays.asList(
                    (k, v) -> v.getSource().equals("online"),
                    (k, v) -> v.getSource().equals("Inside"),
                    (k, v) -> v.getSource().equals("ThirdParty"));
            // 进行后续处理
          return mergedStream.mapValues(stringToEvent).filter((k, v) -> v != null)
                    .selectKey(selectKeyKeyValueMapper).mapValues(kafkaValueMapper).branch(predicates.toArray(new Predicate[predicates.size()]));

        };


    }


}
