package com.prokarma.producer.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.prokarma.producer.model.Address;
import com.prokarma.producer.model.MessageRequest;
import com.prokarma.producer.model.MessageRequest.CustomerStatusEnum;
import com.prokarma.producer.service.DefaultPublishService;
import com.prokarma.producer.service.PublisherService;
import com.prokarma.producer.util.ObjectMapperUtil;

@ExtendWith(MockitoExtension.class)
class MessageProducerControllerTest {

	private static final String RESPONSE_STRING = "Published Message sucessfully";

	@InjectMocks
	private PublisherController messageProducerController;

	@Mock
	private DefaultPublishService kafkaSenderService;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(messageProducerController).build();
	}

	@Test
	void testInvokeSoapCallWithQueryProfile() throws Exception {
		MessageRequest message = buildMessageRequestObject();
		Mockito.when(kafkaSenderService.send(any())).thenReturn("Published Message sucessfully");
		MvcResult result = mockMvc
				.perform(post("/produce/send-message").contentType(MediaType.APPLICATION_JSON)
						.headers(buildHttpHeaders()).content(ObjectMapperUtil.returnJsonFromObject(message)))
				.andExpect(status().is(200)).andReturn();
		String responseString = result.getResponse().getContentAsString();
		assertNotNull(responseString);
		assertEquals(RESPONSE_STRING, responseString);
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
		messageRequest.setFirstName("David");
		messageRequest.setLastName("Willam");
		messageRequest.setMobileNumber("9912101210");
		return messageRequest;
	}

	private Address buildAddressObject() {
		Address address = new Address();
		address.setAddressLine1("Shivane");
		address.setAddressLine2_("Pune");
		address.setPostalCode(411023);
		address.setStreet("Pune");
		return address;
	}

	private HttpHeaders buildHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set("Authorization", "Bearer eyJraWQiOiIzd2V5blJZX0VQSmxVdElna2h1ckNmUnVKbDJidGJCdkFq");
		return headers;
	}
}
