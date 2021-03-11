package com.api.mail.manag.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.mail.manag.entity.Info;

@SpringBootTest // run entiére le ApplicationContext
public class ValidationFieldsOfInfoServiceImplTest {

	@Autowired
	ValidationFieldsOfInfoService validationFieldsOfInfoService;

	Info infoInvalide;
	Info infoInvalide1;
	Info infoValide;

	@BeforeEach
	public void init() {// :: stup()
		infoInvalide = new Info();
		infoInvalide.setTitle("Pour tester notre traitement de text:121");
		infoInvalide.setFirstName("achraf123");
		// (+) si un clé n'est pas de correspondance
		infoInvalide.setLastName("farhca");
		infoInvalide.setPhone("045788912");
		infoInvalide.setEmployee(true);

		infoInvalide1 = new Info();

		infoValide = new Info();
		infoValide.setTitle("Pour tester notre traitement de text");
		infoValide.setFirstName("achraf");
		// (+) si un clé n'est pas de correspondance
		infoValide.setLastName("farhca");
		infoValide.setPhone("0645788912");
		infoValide.setEmployee(true);

	}

	@Test
	void when_give_Info_it_should_return_empty() {

		String allMessageV = validationFieldsOfInfoService.errorMessagesOfEachInvalidField(infoValide);

		// Assert
		assertThat(allMessageV).isEmpty();// check is not error messages

	}

	@Test
	void when_Give_Info_it_Should_Return_ErrorMessage() {

		String allMessageInV = validationFieldsOfInfoService.errorMessagesOfEachInvalidField(infoInvalide);

		// Assert
		assertThat(allMessageInV).isNotEmpty();// check is error messages

		// (+) contient message d'erreur
		assertThat(allMessageInV)
				.containsIgnoringCase("Title ne contient que les caractères  et/ou (.) et/ou les chiffres.");
		assertThat(allMessageInV).containsIgnoringCase("Votre Prénom ne contient que les caractères.");
		assertThat(allMessageInV).containsIgnoringCase("numero de téléphone doit commencer par (05)/(06)/(07).");

		assertThat(allMessageInV).containsIgnoringCase("Numéro Téléphone doit contenir 10 chiffres.");

	}

	@Test
	void when_give_Info_it_should_return_errorMessage_requert() {

		String allMessageInV = validationFieldsOfInfoService.errorMessagesOfEachInvalidField(infoInvalide1);

		// Assert
		assertThat(allMessageInV).isNotEmpty();// check is error messages

		// (+) contient message d'erreur
		assertThat(allMessageInV).containsIgnoringCase("Title,Votre Prénom,Téléphone est(sont) oblogatoire(s).");
		assertThat(allMessageInV).containsIgnoringCase("est(sont) oblogatoire(s).");

	}

}
