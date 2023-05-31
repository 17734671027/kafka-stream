package com.jcloud.kafka.filter;


import com.jcloud.kafka.pojo.Event;
import org.apache.kafka.streams.kstream.Predicate;


/**
 * @author xuyong96
 */
public interface KafkaFilter extends Predicate<String, Event> {


	boolean filter(Event event);

	String type();
}
