package com.api.mail.manag.service.email;

import static com.api.mail.manag.configuration.MailConstants.PASSWORD;
import static com.api.mail.manag.configuration.MailConstants.USERNAME;
import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.getTemplateHTMLMailValid;
import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.subjectMailValid;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendValidationEmailServiceImpl implements SendValidationEmailService {

	/*
	 * config SendMail dans 'EmailConfiguration'
	 *  la bibliothèque springMail send
	 * email et il prend en charge les messages MIME javaMail et spring Mail sont 2
	 * support par Spring framwork(et similaire au niveau implemantation)
	 */
	@Autowired
	JavaMailSenderImpl mailSender;

	@Value("${upload.path}")
	private String path;

	/*
	 * Envoyer mail de validation avec les messages d'erreur de validation de chaque
	 * champ non valide
	 */
	public void sendValidationEmail(String messages, String mailTo) throws MessagingException, IOException {

		String errorMessagesWithTemplateHtml = getTemplateHTMLMailValid(messages);

		mailSender.setUsername(USERNAME);// (+) login,username
		mailSender.setPassword(PASSWORD); // (+) password

		// MimeMessage :class pour la préparation des messages MIME
		MimeMessage msg = mailSender.createMimeMessage();

		/*
		 * MimeMessageHelper: classe d'assistance pour la création de messages MIME. Il
		 * prend en charge les images, les pièces jointes typiques et le contenu texte
		 * dans une mise en page HTML ( true = multipart message)
		 */
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setFrom(USERNAME); // (+) email from
		helper.setTo(mailTo); // (+) emial to
		helper.setSubject(subjectMailValid);

		// true = text/html; on peut utiliser mais apporter probléme de é à
		// helper.setText(errorMessagesWithTemplateHtml, true);

		// pour éviter probléme de é à
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(errorMessagesWithTemplateHtml, "text/html;charset=utf-8");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		msg.setContent(multipart);

		mailSender.send(msg);
	}

}
