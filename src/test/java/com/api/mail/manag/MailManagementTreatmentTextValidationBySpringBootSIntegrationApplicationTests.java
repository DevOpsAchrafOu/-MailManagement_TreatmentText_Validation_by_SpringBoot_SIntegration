package com.api.mail.manag;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailManagementTreatmentTextValidationBySpringBootSIntegrationApplicationTests {

	/*
	 * si on va utiliser @Autowired automatiquement invoquer @SpringBootRun qui
	 * prendre des 4,5 secondes au lieu millisecondes Mais un bon test unitaire ne
	 * prend que des millisecondes donc est l'écriture de tests unitaires sans
	 * Spring Boot mais qlq fois on a bosoin de démarer context Spring pour par
	 * exemple save/get/chercher dans BD.
	 */

	@Test
	void contextLoads() {
	}

}
