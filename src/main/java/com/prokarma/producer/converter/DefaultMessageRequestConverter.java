package com.prokarma.producer.converter;

import org.springframework.stereotype.Component;
import com.prokarma.producer.model.Address;
import com.prokarma.producer.model.AddressProducerRequest;
import com.prokarma.producer.model.MessageProducerRequest;
import com.prokarma.producer.model.MessageRequest;

@Component
public class DefaultMessageRequestConverter
        implements Converter<MessageRequest, MessageProducerRequest> {

    public MessageRequest maskConversion(MessageRequest messageRequest) {
        messageRequest.setCustomerNumber(
                messageRequest.getCustomerNumber().replaceAll(("\\w(?<=\\w{7})"), "*"));
        messageRequest
                .setBirthDate(messageRequest.getBirthDate().replaceAll(("\\d(?=[^-]*?-)"), "*"));
        messageRequest.setEmail(messageRequest.getEmail().replaceAll((".(?<!.{5})"), "*"));

        return messageRequest;
    }

    @Override
    public MessageProducerRequest convert(MessageRequest messageRequest) {
        return buildMessageProducerRequest(messageRequest);
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
