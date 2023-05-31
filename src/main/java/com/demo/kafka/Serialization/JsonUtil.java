package com.demo.kafka.Serialization;
/**
 *
 * @author sangchengliang
 * 
 */

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class JsonUtil {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	}

	public static String objectToString(Object obj) {
		if (obj == null) {
			return null;
		}
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			log.error("Exception when object to string:{}, the object is:{}", e.getMessage(), obj);
			return null;
		}
	}

	public static <T> T stringToObject(String json, Class<T> cla) throws Exception {

		if (json == null) {
			return null;
		}
		try {
			return mapper.readValue(json, cla);
		} catch (Exception e) {
			log.warn("Exception when string to object:{}, the string is:{}", e.getMessage(), json);
			return null;
		}
	}
	
	
	

}
