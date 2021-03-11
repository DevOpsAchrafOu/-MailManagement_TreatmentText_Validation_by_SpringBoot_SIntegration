package com.api.mail.manag.service.email;

import java.io.IOException;

import javax.mail.MessagingException;

public interface SendValidationEmailService {

	/*
	 * envoyer mail de validation avec les messages d'erreur de validation de chaque
	 * champ non valide
	 */
	void sendValidationEmail(String messages, String mailTo) throws MessagingException, IOException;
}
