package com.prokarma.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.prokarma.producer.converter.DefaultMessageRequestConverter;
import com.prokarma.producer.converter.DefaultMessageRequestMaskConverter;
import com.prokarma.producer.exceptions.PublisherServiceException;
import com.prokarma.producer.model.MessageProducerRequest;
import com.prokarma.producer.model.MessageRequest;
import com.prokarma.producer.model.MessageResponse;

@Service
public class DefaultPublishService implements PublisherService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultPublishService.class);

    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${cloudkarafka.topic}")
    private String kafkaTopic;

    @Autowired
    private DefaultMessageRequestConverter messageRequestConverter;

    @Autowired
    private DefaultMessageRequestMaskConverter messageRequestMaskConverter;

    public DefaultPublishService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public MessageResponse publishMessage(MessageRequest message) {
        try {
            MessageRequest messageRequest = messageRequestMaskConverter.convert(message);
            logger.info("Start to Publish message and message is {}", messageRequest);
            MessageProducerRequest messageProducerRequest =
                    messageRequestConverter.convert(messageRequest);
            kafkaTemplate.send(kafkaTopic, messageProducerRequest);
            logger.info("End to Publish message and message is {}", messageRequest);
        } catch (Exception ex) {
            throw new PublisherServiceException(
                    String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());
        }
        return buildMessageResponse();

    }

    private MessageResponse buildMessageResponse() {
        MessageResponse response = new MessageResponse();
        response.setData("Published Message sucessfully");
        response.setStatus("success");
        return response;
    }


}
