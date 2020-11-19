package com.prokarma.producer.converter;

public interface Converter<I, O> {

    O convert(I input);

}
