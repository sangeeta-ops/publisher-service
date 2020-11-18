package com.prokarma.producer.masking;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prokarma.producer.model.Address;
import com.prokarma.producer.model.CustomerStatusEnum;
import com.prokarma.producer.model.MessageRequest;

@ExtendWith(MockitoExtension.class)
public class CustomerDataMaskingUtilTest {

	@Test
	void testMaskCustomerDataSuccess() throws Exception {
		MessageRequest messageRequest = buildMessageRequestObject();
		MessageRequest maskMessageRequest = CustomerDataMaskingUtil.maskCustomerData(messageRequest);
		assertNotNull(maskMessageRequest);
		assertNotEquals(maskMessageRequest.getCustomerNumber(), messageRequest.getCustomerNumber());
	}

	@Test
	void testMaskCustomerDataFail() throws Exception {
		MessageRequest messageRequest = buildMessageRequestObject();
		messageRequest.setCustomerNumber("123");
		MessageRequest maskMessageRequest = CustomerDataMaskingUtil.maskCustomerData(messageRequest);
		assertNotNull(maskMessageRequest);
		assertEquals(maskMessageRequest.getCustomerNumber(), messageRequest.getCustomerNumber());
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
