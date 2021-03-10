package com.api.mail.manag.service.email;

import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.message5;
import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.styleEtoil;
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
import com.api.mail.manag.service.ValidationFieldsOfInfoService;

@MessageEndpoint
public class EmailReceiverService {

	@Autowired
	private MassageTextProcessorService MassageTextProcessorService;

	@Autowired
	private InfoRepository InfoRepository;

	@Autowired
	private ValidationFieldsOfInfoService validationFieldsOfInfoService;

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
			emailContent.setEmailFrom(emialFrom);
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

		// (+) si contenu est String
		if (content instanceof String) {
			if (bodyPart.isMimeType("text/plain")) {
				String contentMail = content.toString();
				EmailObject at = new EmailObject();
				at.setBody(contentMail);
				at.setEmailFrom(email);
				result.add(at);
				return result;
			} else {
				return new ArrayList<>();
			}
		}

		// (+) si pour plisieurs fichier
		if (content instanceof Multipart) {
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

			String msgVal = "";// messages de validation les info
			Boolean isInvalidField = false;// true ;si les info non valider

			// (+) traitement de texte et get Key/Value
			HashMap<String, String> infoKeyValue = MassageTextProcessorService.extractInfoByPatterns(mail.getBody(),
					patternsInitialise());

			if (infoKeyValue != null) {

				// (+) Key/Value to Info object
				Info info = MassageTextProcessorService.InfoFromMapToObjet(infoKeyValue);
				info.setEmailFrom(mail.getEmailFrom());

				// (+) ressembler les messages d'erreur de validation de chaque champ non valide
				msgVal = validationFieldsOfInfoService.errorMessagesOfEachInvalidField(info);

				// (+) si les info valider
				if (msgVal.isEmpty()) {

					// (+) save in BD
					InfoRepository.save(info);
					System.out.println("*********** Email Received is Added ***********");

				} else {

					// (+)si les info non valider; send mail de validation
					isInvalidField = true;
				}
			} else {

				msgVal = styleEtoil + message5;
				// (+)si les info non valider; send mail de validation
				isInvalidField = true;
			}

			// (+)si les info non valider; send mail de validation
			if (isInvalidField) {
				System.out.println("*********** Email Received is InValider ***********");
			}

		});

	}

}
