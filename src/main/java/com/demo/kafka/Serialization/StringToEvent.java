package com.demo.kafka.Serialization;

import com.alibaba.fastjson2.JSON;
import com.demo.kafka.pojo.Event;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.invoke.MethodHandles;

@Component("stringToEvent")
public class StringToEvent implements ValueMapper<String, Event> {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


	@Override
	public Event apply(String value) {
		try {
			if (StringUtils.isEmpty(value)) {
				log.info("Gain empty value from event");
				return null;
			}
			log.info("Gain value from event: {}", JSON.toJSONString(value));

			return JsonUtil.stringToObject(value, Event.class);

		} catch (Exception e) {
			return null;
		}
	}

}
