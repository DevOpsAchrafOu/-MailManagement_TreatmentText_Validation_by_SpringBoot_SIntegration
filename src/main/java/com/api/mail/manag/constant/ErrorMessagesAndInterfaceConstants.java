package com.api.mail.manag.constant;

public class ErrorMessagesAndInterfaceConstants {

	/*
	 * Message d'erreur de chaque champ invalide (signifier la réponse automatique
	 * pour validation les informarion email)
	 */
	/** ----------------------- Message d'erreur -------------------------- **/
	public static final String subjectMailValid = " Mail de la validation les information";
	public static final String message1 = " est(sont) oblogatoire(s).";
	public static final String message2 = " doit contenir au moins 4 caractères.";
	public static final String message22 = " Numéro Téléphone doit contenir 10 chiffres.";
	public static final String message3 = " ne contient que les caractères.";
	public static final String message33 = " ne contient que les caractères  et/ou (.) et/ou les chiffres.";
	public static final String message333 = " doit contenir au moins des chiffres et des caractères.";
	public static final String message4 = " Le numero de téléphone doit commencer par (05)/(06)/(07).";

	public static final String message5 = " Le contenu de l'email ne correspond pas le format standard; il doit être sous forme (Clé : Valeur) pour chaque information";

	public static final String v_title = "Title,";
	public static final String v_firstName = "Votre Prénom,", v_lastName = "Votre Nom,", v_phone = "Téléphone,";

	public static final String styleEtoil = "<span style='font-size: 1.4em; font-weight: 800;color: #3c9691;'> * </span>";
	public static final String styleBR = "<br>";
}
