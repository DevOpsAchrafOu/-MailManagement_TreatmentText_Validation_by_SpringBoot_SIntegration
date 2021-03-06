package com.api.mail.manag.service.email;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.api.mail.manag.entity.Info;

@Service
public class MassageTextProcessorServiceImpl implements MassageTextProcessorService {

	public int NumMatcherOfKey = 0;

	/*
	 * Methode qui va extraire les informations de message Via Patterns (Traitement
	 * de texte et obtenir des données bien organiser)
	 */
	public HashMap<String, String> extractInfoByPatterns(String messageCont, Map<String, String> patterns) {
		NumMatcherOfKey = 0;
		List<String> list = new ArrayList<>();

		for (String m : messageCont.split("\\r\\n"))
			if (!m.isEmpty()) {
				list.add(m.trim());
				// sup!!!!!!!!!!!!!!
				// System.out.println("---> " + m.trim());
			}

//		(+) faire la correspondance et remplacer valeur à certain clé dans le map par un info
		patterns.forEach((k, v) -> {

			Pattern p = Pattern.compile(v);
			list.stream().map(p::matcher).filter(Matcher::matches).findFirst().ifPresent(matcher -> {
				patterns.replace(k,
						((matcher.group().split(":").length != 1) ? matcher.group().split(":")[1].trim() : ""));
				this.NumMatcherOfKey++;
			});

		});

//		 (+) contrainte : nombre de correspond est doit être plus de 2
		if (this.NumMatcherOfKey > 2) {
			return (HashMap<String, String>) patterns;
		} else {
			System.out.println("********** Plus de 2 clé ne correspondent pas ************");
			return null;
		}

	}

	// info from Collection(Map) to Object'Info'
	@Override
	public Info InfoFromMapToObjet(Map<String, String> infoKeyValue) {

		Info info = new Info();

		info.setTitle(getValue("title", infoKeyValue));
		info.setFirstName(getValue("firstName", infoKeyValue));
		info.setLastName(getValue("lastName", infoKeyValue));
		info.setPhone(getValue("phone", infoKeyValue));

//	(+) traiter boolean String
		String fc = getValue("employee", infoKeyValue);// (+)client salarié ou non
		if (fc.toLowerCase().contains("oui")) {
			info.setEmployee(true);
		} else {
			info.setEmployee(false);
		}

		return info;
	}

//	remplacer vide à certain clé dans le map; si n'est pas de correspondance
	private String getValue(String key, Map<String, String> info) {

		return info.get(key).contains(".*") ? "" : info.get(key);
	}
}
