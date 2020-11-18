package com.prokarma.producer.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prokarma.producer.model.Address;
import com.prokarma.producer.model.CustomerStatusEnum;
import com.prokarma.producer.model.MessageRequest;

class ObjectMapperUtilTest {

    @Mock
    private ObjectMapper mapper;

    @Test
    public void testObjectMapperUtilWhenDomainObjectPassed() throws Exception {
        String expected =
                "{\"customerNumber\":\"CUST123456\",\"firstName\":\"David David\",\"lastName\":\"Willam Willam\",\"birthDate\":null,\"country\":\"India\",\"countryCode\":\"IN\",\"mobileNumber\":\"9912101210\",\"email\":\"David@gmail.com\",\"customerStatus\":\"OPEN\",\"address\":{\"addressLine1\":\"Shivane\",\"addressLine2 \":\"Pune\",\"street\":\"Pune\",\"postalCode\":\"41102\"}}";
        MessageRequest messageRequest = buildMessageRequestObject();
        String result = ObjectMapperUtil.returnJsonFromObject(messageRequest);
        Assertions.assertEquals(expected, result);
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

}
