package com.demo.kafka.filter;


import com.demo.kafka.pojo.Event;
import org.apache.kafka.streams.kstream.Predicate;


/**
 * @author xuyong96
 */
public interface KafkaFilter extends Predicate<String, Event> {


	boolean filter(Event event);

	String type();
}
