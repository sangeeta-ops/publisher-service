package com.prokarma.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.prokarma.producer.masking.CustomerDataMasking;
import com.prokarma.producer.model.MessageRequest;

@Service
public class DefaultKafkaSenderService implements KafkaSenderService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultKafkaSenderService.class);

	private KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${cloudkarafka.topic}")
	private String kafkaTopic;

	public DefaultKafkaSenderService(KafkaTemplate<String, Object> template) {
		this.kafkaTemplate = template;
	}

	@Override
	public String send(MessageRequest message) throws Exception {
		MessageRequest messageRequest = CustomerDataMasking.maskCustomerData(message);
		logger.info("Start to Publish message and message is {}", messageRequest);
		kafkaTemplate.send(kafkaTopic, message);
		logger.info("End to Publish message and message is {}", messageRequest);
		return "Published Message sucessfully";
	}
}