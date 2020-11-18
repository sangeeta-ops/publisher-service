package com.prokarma.producer.masking;

import org.springframework.beans.BeanUtils;

import com.prokarma.producer.model.MessageRequest;

public class CustomerDataMaskingUtil {

	public static MessageRequest maskCustomerData(MessageRequest message) throws Exception {
		MessageRequest maskRequest = new MessageRequest();
		BeanUtils.copyProperties(message, maskRequest);
		maskRequest.setCustomerNumber(maskString(message.getCustomerNumber(), message.getCustomerNumber().length() - 4,
				message.getCustomerNumber().length(), '*'));
		maskRequest.setEmail(maskString(message.getEmail(), 0, 4, '*'));
		maskRequest.setMobileNumber(maskString(message.getEmail(), 0, 4, '*'));
		return maskRequest;

	}

	private static String maskString(String strText, int start, int end, char maskChar) throws Exception {
		int maskLength = end - start;
		if (maskLength == 0 || maskLength > strText.length())
			return strText;
		StringBuilder sbMaskString = new StringBuilder(maskLength);
		for (int i = 0; i < maskLength; i++) {
			sbMaskString.append(maskChar);
		}
		return strText.substring(0, start) + sbMaskString.toString() + strText.substring(start + maskLength);
	}

}
