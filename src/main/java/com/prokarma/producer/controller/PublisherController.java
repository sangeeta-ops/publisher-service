package com.prokarma.producer.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.prokarma.producer.masking.CustomerDataMaskingUtil;
import com.prokarma.producer.model.MessageRequest;
import com.prokarma.producer.model.MessageResponse;
import com.prokarma.producer.service.PublisherService;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    private static final Logger logger = LoggerFactory.getLogger(PublisherController.class);

    @Autowired
    PublisherService publisherService;

    @RequestMapping(method = RequestMethod.POST, path = "/publish-message")
    public ResponseEntity<MessageResponse> publishService(
            @Valid @RequestBody MessageRequest messageRequest,
            @RequestHeader("Application-Id") String applicationId,
            @RequestHeader("Activity-Id") String activityId,
            @RequestHeader("Authorization") String authorization) throws Exception {
        MessageRequest maskMessageRequest =
                CustomerDataMaskingUtil.maskCustomerData(messageRequest);
        logger.info("messageRequest : {} ", maskMessageRequest);
        MessageResponse response = publisherService.publishMessage(messageRequest);
        ResponseEntity<MessageResponse> responseEnity =
                new ResponseEntity<MessageResponse>(response, HttpStatus.OK);
        return responseEnity;
    }

}
