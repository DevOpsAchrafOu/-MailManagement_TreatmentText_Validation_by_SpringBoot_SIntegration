package com.api.mail.manag.configuration;

import static com.api.mail.manag.configuration.MailConstants.HOST;
import static com.api.mail.manag.configuration.MailConstants.PASSWORD;
import static com.api.mail.manag.configuration.MailConstants.PORT;
import static com.api.mail.manag.configuration.MailConstants.PROTOCOL;
import static com.api.mail.manag.configuration.MailConstants.USERNAME;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.mail.ImapIdleChannelAdapter;
import org.springframework.integration.mail.ImapMailReceiver;
import org.springframework.messaging.SubscribableChannel;

@Configuration
@EnableIntegration
public class EmailConfiguration {

	// Foncution qui obtenir les propriétés du serveur
	public Properties javaMailProperties() {
		Properties javaMailProperties = new Properties();

		// server setting
		javaMailProperties.setProperty("mail.store.protocol", "imaps");
		// SSL setting
		javaMailProperties.setProperty(String.format("mail.%s.socketFactory.class", PROTOCOL),
				"javax.net.ssl.SSLSocketFactory");
		javaMailProperties.setProperty(String.format("mail.%s.socketFactory.fallback", PROTOCOL), "false");
		javaMailProperties.setProperty("mail.debug", "false");
		javaMailProperties.setProperty(String.format("mail.%s.ssl", PROTOCOL), "true");

		return javaMailProperties;
	}

	/*
	 * (marquer des messages lus) sont effectuées après la réception des messages,
	 * mais avant de traité.
	 */
	@Bean
	public ImapMailReceiver mailReceiver() throws UnsupportedEncodingException {
		ImapMailReceiver mailReceiver = new ImapMailReceiver(getEncoderUrl());
		mailReceiver.setJavaMailProperties(javaMailProperties());
		mailReceiver.setShouldDeleteMessages(false);// pas supprimer les messages
		mailReceiver.setShouldMarkMessagesAsRead(true);// marquer les massager lu(pas propriéte flag.Dark)
		mailReceiver.setSimpleContent(true);
		return mailReceiver;
	}

	public String getEncoderUrl() throws UnsupportedEncodingException {
		String user = URLEncoder.encode(USERNAME, "UTF-8");
		String password = URLEncoder.encode(PASSWORD, "UTF-8");
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append("imaps://").append(user).append(":").append(password).append("@").append(HOST).append(":")
				.append(PORT).append("/inbox");
		return urlBuilder.toString();
	}

	/*
	 * C'est le Channel par lequel les messages sont travers d'un système à un autre
	 * et dans notre cas le Channel utiliser juste recevoire des e-mails et on a
	 * utiliser un @ServiceActivator pour recevoire les e-mails
	 */
	@Bean
	public SubscribableChannel mailChannel() {
		return MessageChannels.direct().get();
	}

	@Bean
	public ImapIdleChannelAdapter adapter() throws UnsupportedEncodingException {
		ImapIdleChannelAdapter imapIdleChannelAdapter = new ImapIdleChannelAdapter(mailReceiver());
		imapIdleChannelAdapter.setOutputChannel(mailChannel());//
		imapIdleChannelAdapter.afterPropertiesSet();
		return imapIdleChannelAdapter;
	}

}