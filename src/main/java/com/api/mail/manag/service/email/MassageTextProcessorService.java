package com.api.mail.manag.service.email;

import java.util.HashMap;
import java.util.Map;

import com.api.mail.manag.entity.Info;

public interface MassageTextProcessorService {

	/*
	 * Methode qui va extraire les informations de message Via Patterns (Traitement
	 * de texte et obtenir des données bien organiser)
	 */
	HashMap<String, String> extractInfoByPatterns(String messageCont, Map<String, String> patterns);

	// info from Collection(Map) to Object'Info'
	Info InfoFromMapToObjet(Map<String, String> infoKeyValue);
}
