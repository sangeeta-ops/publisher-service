package com.prokarma.producer.converter;

public interface ProducerConverter<I, O> {

    O convert(I input);

}
