package com.prokarma.producer.service;

import com.prokarma.producer.model.MessageRequest;

public interface KafkaSenderService {

	public String send(MessageRequest message) throws Exception;

}
