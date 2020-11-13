package com.prokarma.producer.service;

import com.prokarma.producer.model.MessageRequest;

public interface PublisherService {

	public String send(MessageRequest message) throws Exception;

}
