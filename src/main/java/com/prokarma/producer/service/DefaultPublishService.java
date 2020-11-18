package com.prokarma.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;

import com.prokarma.producer.exceptions.PublisherServiceException;
import com.prokarma.producer.masking.CustomerDataMaskingUtil;
import com.prokarma.producer.model.Address;
import com.prokarma.producer.model.AddressProducerRequest;
import com.prokarma.producer.model.MessageProducerRequest;
import com.prokarma.producer.model.MessageRequest;
import com.prokarma.producer.model.MessageResponse;

@Service
public class DefaultPublishService implements PublisherService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPublishService.class);

	private KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${cloudkarafka.topic}")
	private String kafkaTopic;

	MessageResponse response;

	public DefaultPublishService(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public MessageResponse publishMessage(MessageRequest message) throws Exception {
		try {
			MessageRequest messageRequest = CustomerDataMaskingUtil.maskCustomerData(message);
			logger.info("Start to Publish message and message is {}", messageRequest);
			MessageProducerRequest messageProducerRequest = buildMessageProducerRequest(messageRequest);
			kafkaTemplate.send(kafkaTopic, messageProducerRequest);
			logger.info("End to Publish message and message is {}", messageRequest);
			response = buildMessageResponse();
			return response;
		} catch (Exception e) {
			throw new PublisherServiceException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
					"Processing failed while invoking to publishMessage Service  : {}" + e.getMessage());

		}

	}

	private MessageResponse buildMessageResponse() {
		MessageResponse response = new MessageResponse();
		response.setData("Published Message sucessfully");
		response.setStatus("success");
		return response;
	}

	private MessageProducerRequest buildMessageProducerRequest(MessageRequest messageRequest) {
		MessageProducerRequest messageProducerRequest = new MessageProducerRequest();
		messageProducerRequest.setAddress(buildMessageAddressRequest(messageRequest.getAddress()));
		messageProducerRequest.setBirthDate(messageRequest.getBirthDate());
		messageProducerRequest.setCountry(messageRequest.getCountry());
		messageProducerRequest.setCountryCode(messageRequest.getCountryCode());
		messageProducerRequest.setCustomerNumber(messageRequest.getCustomerNumber());
		messageProducerRequest.setCustomerStatus(messageRequest.getCustomerStatus());
		messageProducerRequest.setEmail(messageRequest.getEmail());
		messageProducerRequest.setFirstName(messageRequest.getEmail());
		messageProducerRequest.setLastName(messageRequest.getLastName());
		messageProducerRequest.setMobileNumber(messageRequest.getMobileNumber());
		return messageProducerRequest;
	}

	private AddressProducerRequest buildMessageAddressRequest(Address address) {
		AddressProducerRequest addressProducerRequest = new AddressProducerRequest();
		addressProducerRequest.setAddressLine1(address.getAddressLine1());
		addressProducerRequest.setAddressLine2(address.getAddressLine2());
		addressProducerRequest.setPostalCode(address.getPostalCode());
		addressProducerRequest.setStreet(address.getStreet());
		return addressProducerRequest;
	}

}