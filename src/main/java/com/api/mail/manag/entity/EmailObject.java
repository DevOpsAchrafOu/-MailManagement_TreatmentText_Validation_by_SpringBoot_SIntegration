package com.api.mail.manag.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Object for Email send and receiver */

@Data
@AllArgsConstructor
@NoArgsConstructor // pour gérer les getter, setter et les constructeurs automatique
public class EmailObject {

	private String subject;

	private String emailTo;

	private String body; // contenu

	// email et password de compte Email qui on va utiliser pour envoyer
	private String emailFrom;

	private String Password;

}
