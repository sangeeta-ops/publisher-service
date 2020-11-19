package com.prokarma.producer.converter;

import org.springframework.stereotype.Component;
import com.prokarma.producer.constants.PublisherConstants;
import com.prokarma.producer.model.MessageRequest;

@Component
public class DefaultMessageRequestMaskConverter implements MaskConverter<MessageRequest> {

    @Override
    public MessageRequest convert(MessageRequest messageRequest) {
        messageRequest.setCustomerNumber(messageRequest.getCustomerNumber().replaceAll(
                (PublisherConstants.CUSTOMER_NUMBER_REGEX_EXPRESSION.getRegexExpression()),
                PublisherConstants.MASK_CONSTANTS.getRegexExpression()));
        messageRequest.setBirthDate(messageRequest.getBirthDate().replaceAll(
                (PublisherConstants.BIRTH_DATE_REGEX_EXPRESSION.getRegexExpression()),
                PublisherConstants.MASK_CONSTANTS.getRegexExpression()));
        messageRequest.setEmail(messageRequest.getEmail().replaceAll(
                (PublisherConstants.EMAIL_ID_REGEX_EXPRESSION.getRegexExpression()),
                PublisherConstants.MASK_CONSTANTS.getRegexExpression()));
        return messageRequest;

    }

}
