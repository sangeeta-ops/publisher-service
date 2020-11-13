package com.prokarma.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import com.prokarma.producer.exceptions.PublisherServiceException;
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
		try {
			MessageRequest messageRequest = CustomerDataMaskingUtil.maskCustomerData(message);
			logger.info("Start to Publish message and message is {}", messageRequest);
			kafkaTemplate.send(kafkaTopic, message);
			logger.info("End to Publish message and message is {}", messageRequest);
			return "Published Message sucessfully";
		} catch (Exception e) {
			throw new PublisherServiceException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
					"Processing failed while invoking to SendIdVerificationDataToRms API call : {}" + e.getMessage());

		}

	}

	
}