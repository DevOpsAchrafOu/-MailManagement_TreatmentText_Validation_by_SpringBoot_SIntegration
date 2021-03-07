package com.api.mail.manag.service.email;

import static com.api.mail.manag.constant.PatternsInitialise.patternsInitialise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import com.api.mail.manag.dao.InfoRepository;
import com.api.mail.manag.entity.EmailObject;
import com.api.mail.manag.entity.Info;

@MessageEndpoint
public class EmailReceiverService {

	@Autowired
	private MassageTextProcessorService MassageTextProcessorService;

	@Autowired
	private InfoRepository InfoRepository;

	// Fonction pour consommer de message et traiter
	@ServiceActivator(inputChannel = "mailChannel")
	public void handleMessage(Message<?> message) throws Exception {
		MimeMessage mimeMessage = (MimeMessage) message.getPayload();

		String subject = mimeMessage.getSubject().toLowerCase();

		// (+) transférer les email de subject contient "traiter" vers étape suivant
		if (subject.contains("traiter")) {
			// (+) extract info by listMail and save with validation each feild
			extractInfoByListMailAndSaveWithValidation(getEmailObjects(mimeMessage));
		}

	}

	/*
	 * get text from message; si contenu est découpé plusieurs pièces ou un seul
	 * pièces
	 */
	private List<EmailObject> getEmailObjects(MimeMessage mimeMessage) throws Exception {
		Object content = mimeMessage.getContent();
		// get Email From
		String emialFrom = ((InternetAddress) mimeMessage.getFrom()[0]).getAddress();

		List<EmailObject> result = new ArrayList<EmailObject>();
		if (content instanceof String) {

			EmailObject emailContent = new EmailObject();
			emailContent.setBody(content.toString());
			emailContent.setEmailTo(emialFrom);
			result.add(emailContent);
			return result;
		}
		if (content instanceof Multipart) {
			Multipart multipart = (Multipart) content;

			for (int i = 0; i < multipart.getCount(); i++) {
				result.addAll(getEmailContents(multipart.getBodyPart(i), emialFrom));
			}
			return result;
		}
		return null;
	}

	// get text from message; si contenu est découpé plusieurs pièces (BodyPart)
	private List<EmailObject> getEmailContents(BodyPart bodyPart, String email) throws Exception {
		List<EmailObject> result = new ArrayList<EmailObject>();
		Object content = bodyPart.getContent();

		if (content instanceof String) {// contenu est String
			if (bodyPart.isMimeType("text/plain")) {
				String contentMail = content.toString();
				EmailObject at = new EmailObject();
				at.setBody(contentMail);
				at.setEmailTo(email);
				result.add(at);
				return result;
			} else {
				return new ArrayList<>();
			}
		}

		if (content instanceof Multipart) {// pour plisieurs fichier
			Multipart multipart = (Multipart) content;
			for (int i = 0; i < multipart.getCount(); i++) {
				BodyPart part = multipart.getBodyPart(i);
				result.addAll(getEmailContents(part, email));
			}
		}
		return result;
	}

	// extract info by listMail and save with validation each feild
	private void extractInfoByListMailAndSaveWithValidation(List<EmailObject> listEmail) {

		listEmail.forEach(mail -> {

			System.out.println("****** Email received and passed the first filter ******");

			// (+) traitement de texte et get Key/Value
			HashMap<String, String> infoKeyValue = MassageTextProcessorService.extractInfoByPatterns(mail.getBody(),
					patternsInitialise());

			// (+) Key/Value to Info object
			Info info = MassageTextProcessorService.InfoFromMapToObjet(infoKeyValue);

			// (+) save in BD
			InfoRepository.save(info);
			System.out.println("*********** Email Received is Added ***********");

		});

	}

}
