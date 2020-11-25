package com.prokarma.producer.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.prokarma.producer.converter.DefaultMessageRequestMaskConverter;
import com.prokarma.producer.model.MessageRequest;
import com.prokarma.producer.model.MessageResponse;
import com.prokarma.producer.service.PublisherService;

@RestController
@RequestMapping("/publisher/v1")
public class PublisherController {

    private static final Logger logger = LoggerFactory.getLogger(PublisherController.class);

    @Autowired
    private PublisherService publisherService;


    @Autowired
    private DefaultMessageRequestMaskConverter messageRequestMaskConverter;

    @PostMapping(path = "/publish-message")
    public ResponseEntity<MessageResponse> publishService(
            @Valid @RequestBody MessageRequest messageRequest,
            @RequestHeader("Application-Id") String applicationId,
            @RequestHeader("Activity-Id") String activityId,
            @RequestHeader("Authorization") String authorization) {

        MessageRequest maskMessageRequest = messageRequestMaskConverter.convert(messageRequest);
        logger.info("messageRequest : {} ", maskMessageRequest);
        long startTime = System.currentTimeMillis();
        MessageResponse response = publisherService.publishMessage(messageRequest);
        logger.info("Publisher Service required time:{}", System.currentTimeMillis() - startTime);
        logger.info("Status is {}", response.getStatus());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
