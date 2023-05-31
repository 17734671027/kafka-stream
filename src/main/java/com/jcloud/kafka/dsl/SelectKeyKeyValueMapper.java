package com.jcloud.kafka.dsl;

import com.jcloud.kafka.pojo.Event;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.springframework.stereotype.Component;

/**
 * @author xuyong96
 */
@Component("selectKeyKeyValueMapper")
public class SelectKeyKeyValueMapper implements KeyValueMapper<String, Event, String> {

    @Override
    public String apply(String key, Event event) {
        return event.getEventTime() + event.getEventId();
    }

}