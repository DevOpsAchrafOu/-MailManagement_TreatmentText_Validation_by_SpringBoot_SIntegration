package com.api.mail.manag.service;

import com.api.mail.manag.entity.Info;

public interface ValidationFieldsOfInfoService {

	// rassembler les messages d'erreur de validation de chaque champ non valide
	String errorMessagesOfEachInvalidField(Info info);

}
