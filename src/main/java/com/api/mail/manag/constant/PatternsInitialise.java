package com.api.mail.manag.constant;

import java.util.HashMap;
import java.util.Map;

public class PatternsInitialise {

//	Foncution qui va initialiser les Patterns sous-forme clé/valeur

	public static HashMap<String, String> patternsInitialise() {

		Map<String, String> Patterns = new HashMap<String, String>();

		Patterns.put("title", PatternsConstants.title);
		Patterns.put("firstName", PatternsConstants.firstName);
		Patterns.put("lastName", PatternsConstants.lastName);
		Patterns.put("phone", PatternsConstants.phone);
		Patterns.put("employee", PatternsConstants.employee);

		return (HashMap<String, String>) Patterns;
	}

}
