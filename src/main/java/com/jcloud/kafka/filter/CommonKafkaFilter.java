package com.jcloud.kafka.filter;


import com.jcloud.kafka.pojo.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;

/**
 * @author xuyong96
 */
@Component("commonFilter")
public class CommonKafkaFilter implements KafkaFilter {

	private static final String COMMON = "common";


	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public boolean test(String key, Event value) {
		return this.filter(value);
	}

	@Override
	public boolean filter(Event event) {
		if (event == null) {
			log.debug("CommonFilter filter null event");
			return false;
		}

		//添加过滤逻辑

		return true;
	}



	@Override
	public String type() {

		return COMMON;
	}

}
