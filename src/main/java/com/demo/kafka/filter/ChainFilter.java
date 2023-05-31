package com.demo.kafka.filter;


import com.demo.kafka.pojo.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.Set;

@Component
public class ChainFilter implements KafkaFilter {

	private static final String CHAINTYPE = "chain";

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private Set<KafkaFilter> chain;

	public ChainFilter(@Autowired KafkaFilter commonFilter) {
		if (chain == null) {
			chain = new HashSet<>();
		}
		//可以自定义过滤规则 添加进来
		chain.add(commonFilter);
	}

	@Override
	public boolean test(String key, Event value) {
		return this.filter(value);
	}

	@Override
	public boolean filter(Event event) {
		
		if(event == null) {
			return false;
		}
		
		return chain.stream().allMatch(t->{
			boolean result = t.filter(event); 
			if(result) {
				 return result;
			}
			//失败如何处理
			return false;
		});
		
	}

	@Override
	public String type() {
		return CHAINTYPE;
	}

}
