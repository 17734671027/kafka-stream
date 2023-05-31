package com.demo.kafka.Exception;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

import java.lang.invoke.MethodHandles;

/**
 * @author xuyong96
 */
public class CustomDeserializer<T> extends ErrorHandlingDeserializer {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private Deserializer<T> delegate;
    public CustomDeserializer(Deserializer<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            return delegate.deserialize(topic, data);
        } catch (SerializationException e) {
            // 处理异常并返回默认值
            log.warn("Deserialization exception handler data :{}: {}",data, e.getMessage());
            return null;
        }
    }


}
