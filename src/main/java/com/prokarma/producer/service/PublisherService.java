package com.prokarma.producer.service;

import com.prokarma.producer.model.MessageRequest;
import com.prokarma.producer.model.MessageResponse;

public interface PublisherService {

    public MessageResponse publishMessage(MessageRequest message) throws Exception;

}
