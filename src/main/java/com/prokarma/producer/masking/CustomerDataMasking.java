package com.prokarma.producer.masking;

import org.springframework.beans.BeanUtils;

import com.prokarma.producer.model.MessageRequest;

public class CustomerDataMasking {

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

		if (strText == null || strText.equals(""))
			return "";

		if (start < 0)
			start = 0;

		if (end > strText.length())
			end = strText.length();

		if (start > end)
			throw new Exception("End index cannot be greater than start index");

		int maskLength = end - start;

		if (maskLength == 0)
			return strText;

		StringBuilder sbMaskString = new StringBuilder(maskLength);

		for (int i = 0; i < maskLength; i++) {
			sbMaskString.append(maskChar);
		}

		return strText.substring(0, start) + sbMaskString.toString() + strText.substring(start + maskLength);
	}

}
