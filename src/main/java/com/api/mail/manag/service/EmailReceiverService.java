package com.api.mail.manag.service;

import javax.mail.internet.MimeMessage;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@MessageEndpoint
public class EmailReceiverService {

	@ServiceActivator(inputChannel = "mailChannel")
	public void handleMessage(Message<?> message) throws Exception {
		MimeMessage mimeMessage = (MimeMessage) message.getPayload();

		System.out.println("==== > " + mimeMessage.getSubject());

	}
}
