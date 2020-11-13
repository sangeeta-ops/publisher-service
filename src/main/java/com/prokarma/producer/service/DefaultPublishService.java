package com.prokarma.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.prokarma.producer.masking.CustomerDataMaskingUtil;
import com.prokarma.producer.model.MessageRequest;

@Service
public class DefaultPublishService implements PublisherService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPublishService.class);

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${cloudkarafka.topic}")
	private String kafkaTopic;

	@Override
	public String send(MessageRequest message) throws Exception {
		MessageRequest messageRequest = CustomerDataMaskingUtil.maskCustomerData(message);
		logger.info("Start to Publish message and message is {}", messageRequest);
		kafkaTemplate.send(kafkaTopic, message);
		logger.info("End to Publish message and message is {}", messageRequest);
		return "Published Message sucessfully";
	}
}