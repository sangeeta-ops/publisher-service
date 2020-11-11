package com.prokarma.producer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prokarma.producer.masking.CustomerDataMasking;
import com.prokarma.producer.model.MessageRequest;
import com.prokarma.producer.service.DefaultKafkaSenderService;

@RestController
@RequestMapping("/produce")
public class MessageProducerController {

	private static final Logger logger = LoggerFactory.getLogger(MessageProducerController.class);

	@Autowired
	DefaultKafkaSenderService kafkaSender;

	@RequestMapping(method = RequestMethod.POST, path = "/send-message")
	public ResponseEntity<String> consumeService(@RequestBody MessageRequest request) throws Exception {
		MessageRequest messageRequest = CustomerDataMasking.maskCustomerData(request);
		logger.info("messageRequest : {} ", messageRequest);
		String responseStr = kafkaSender.send(request);
		ResponseEntity<String> response = new ResponseEntity<String>(responseStr, HttpStatus.OK);
		return response;
	}

}
