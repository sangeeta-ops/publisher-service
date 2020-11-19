package com.prokarma.producer.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import com.prokarma.producer.converter.DefaultMessageRequestConverter;
import com.prokarma.producer.converter.DefaultMessageRequestMaskConverter;
import com.prokarma.producer.model.Address;
import com.prokarma.producer.model.CustomerStatusEnum;
import com.prokarma.producer.model.MessageRequest;
import com.prokarma.producer.model.MessageResponse;

@ExtendWith(MockitoExtension.class)
class DefaultPublishServiceTest {

    @InjectMocks
    private DefaultPublishService defaultPublishService;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Mock
    private DefaultMessageRequestConverter messageRequestConverter;

    @Mock
    private DefaultMessageRequestMaskConverter messageRequestMaskConverter;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPublishMessageWithSuccess() throws Exception {
        MessageRequest messageRequest = buildMessageRequestObject();
        MessageResponse result = defaultPublishService.publishMessage(messageRequest);
        assertNotNull(result);
        assertEquals(buildMessageResponse(), result);
    }


    private MessageRequest buildMessageRequestObject() {
        MessageRequest messageRequest = new MessageRequest();
        Address address = buildAddressObject();
        messageRequest.setAddress(address);
        messageRequest.setCountry("India");
        messageRequest.setCountryCode("IN");
        messageRequest.setCustomerNumber("CUST123456");
        messageRequest.setCustomerStatus(CustomerStatusEnum.OPEN);
        messageRequest.setEmail("David@gmail.com");
        messageRequest.setFirstName("David David");
        messageRequest.setLastName("Willam Willam");
        messageRequest.setBirthDate("12-02-2020");
        messageRequest.setMobileNumber("9912101210");
        return messageRequest;
    }

    private Address buildAddressObject() {
        Address address = new Address();
        address.setAddressLine1("Shivane");
        address.setAddressLine2("Pune");
        address.setPostalCode("41102");
        address.setStreet("Pune");
        return address;
    }

    private MessageResponse buildMessageResponse() {
        MessageResponse response = new MessageResponse();
        response.setData("Published Message sucessfully");
        response.setStatus("success");
        return response;
    }
}
