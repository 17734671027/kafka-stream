package com.jcloud.kafka.dsl;

import com.jcloud.kafka.pojo.Event;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;


/**
 * @author xuyong96
 * Process the content of the message
 *
 */
@Component("kafkaValueMapper")
public class KafkaValueMapper implements ValueMapper<Event,Event> {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @Override
    public Event apply(Event value) {
        log.info("KafkaValueMapper: {}", value);
        return value;
    }
}
