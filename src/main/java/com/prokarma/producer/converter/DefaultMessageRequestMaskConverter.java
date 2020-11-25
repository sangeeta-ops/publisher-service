package com.prokarma.producer.converter;

import org.springframework.stereotype.Component;
import com.prokarma.producer.constants.PublisherRegexConstants;
import com.prokarma.producer.model.MessageRequest;

@Component
public class DefaultMessageRequestMaskConverter implements MaskConverter<MessageRequest> {

    @Override
    public MessageRequest convert(MessageRequest messageRequest) {
        messageRequest.setCustomerNumber(messageRequest.getCustomerNumber().replaceAll(
                (PublisherRegexConstants.CUSTOMER_NUMBER_REGEX_EXPRESSION.getRegexExpression()),
                PublisherRegexConstants.MASK_CONSTANTS.getRegexExpression()));
        messageRequest.setBirthDate(messageRequest.getBirthDate().replaceAll(
                (PublisherRegexConstants.BIRTH_DATE_REGEX_EXPRESSION.getRegexExpression()),
                PublisherRegexConstants.MASK_CONSTANTS.getRegexExpression()));
        messageRequest.setEmail(messageRequest.getEmail().replaceAll(
                (PublisherRegexConstants.EMAIL_ID_REGEX_EXPRESSION.getRegexExpression()),
                PublisherRegexConstants.MASK_CONSTANTS.getRegexExpression()));
        return messageRequest;

    }

}
