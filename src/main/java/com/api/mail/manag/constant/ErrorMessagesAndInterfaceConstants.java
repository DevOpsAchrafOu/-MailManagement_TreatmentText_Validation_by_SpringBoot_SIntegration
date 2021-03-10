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

	// interface html pour d'affichier les Messages d'erreur
	public static final String getTemplateHTMLMailValid(String message) {
		return "\r\n" + "<table style=\"min-width:320px;\" width=\"100%\">\r\n" + "    <tr>\r\n" + "        <td>\r\n"
				+ "            <!-- logo -->\r\n"
				+ "            <table class=\"flexible\" width=\"600\" align=\"center\" style=\"margin:0 auto;\">\r\n"
				+ "                <tr>\r\n" + "\r\n"
				+ "                    <td class=\"aligncenter \" style=\"padding-top: 10px\">\r\n"
				+ "                        <a href='' style='color: #E8DFCF ;'>\r\n"
				+ "                            <h1 style='color: #FF722D;font-family: Arial;'><strong\r\n"
				+ "                                    style='color: #3c9691 ;font-family: fantasy;font-size: 0.9em;'>A</strong><span\r\n"
				+ "                                    style='font-size: 0.7em;'>chraf</span><strong\r\n"
				+ "                                    style='color: #3c9691 ;font-family: fantasy;font-size: 0.9em;'>O</strong><span\r\n"
				+ "                                    style='font-size: 0.7em; '>u</span></h1>\r\n"
				+ "                        </a>\r\n" + "                    </td>\r\n" + "\r\n"
				+ "                </tr>\r\n" + "            </table>\r\n" + "            <!-- body -->\r\n"
				+ "            <table width=\"100%\">\r\n" + "                <tr>\r\n"
				+ "                    <td class=\"wrapper\" style=\"padding:0 10px;\">\r\n"
				+ "                        <table class=\"flexible\" width=\"600\" align=\"center\" style=\"margin:0 auto;\">\r\n"
				+ "                            <tr style=\"color: black;\">\r\n"
				+ "                                <td class=\"frame\" bgcolor=\"#f6f6f6\" style=\"padding:46px 74px 16px; color: inherit\">\r\n"
				+ "                                    <table width=\"100%\">\r\n"
				+ "                                        <tr>\r\n"
				+ "                                            <td data-color=\"title\" data-size=\"size title\" data-min=\"10\" data-max=\"26\"\r\n"
				+ "                                                data-link-color=\"link title color\"\r\n"
				+ "                                                data-link-style=\"text-decoration:none; \" align=\"center\"\r\n"
				+ "                                                style=\"padding:0 0 65px; font:bold 20px Arial, Helvetica, sans-serif; color: #FF722D;\">\r\n"
				+ "                                                Bonjour,</td>\r\n"
				+ "                                        </tr>\r\n"
				+ "                                        <tr>\r\n"
				+ "                                            <td data-color=\"text\" data-size=\"size text\" data-min=\"10\" data-max=\"26\"\r\n"
				+ "                                                data-link-color=\"link text color\"\r\n"
				+ "                                                data-link-style=\"text-decoration:underline; \"\r\n"
				+ "                                                class=\"aligncenter\"\r\n"
				+ "                                                style=\"padding:0 0 32px; font:12px/18px Arial, Helvetica, sans-serif; \">\r\n"
				+ "                                                <strong style=\"font-size: 1.2em; font-weight: 600;\">S'il vous plait\r\n"
				+ "                                                        :</strong> <br><br>\r\n"
				+ "                                                        \r\n"
				+ "                                                <div style=\"font-size: 0.97em; font-weight: 400;padding-left:23px;color:#000000\">\r\n"
				+ message + "                                                </div>\r\n"
				+ "                                            </td>\r\n"
				+ "                                        </tr>\r\n" + "                                    \r\n"
				+ "\r\n" + "                                        <tr>\r\n"
				+ "                                            <td>\r\n"
				+ "                                                <table align=\"center\" style=\"margin: 20 0 0 0 auto;\">\r\n"
				+ "                                                    <tr>\r\n" + "\r\n"
				+ "                                                        <td style=\"color: black;\" data-color=\"text\" data-size=\"size text\" data-min=\"10\"\r\n"
				+ "                                                            data-max=\"26\" data-link-color=\"link text color\"\r\n"
				+ "                                                            data-link-style=\"text-decoration:underline; \"\r\n"
				+ "                                                            align=\"center\"\r\n"
				+ "                                                            style=\"font:12px/22px Arial, Helvetica, sans-serif;  \">\r\n"
				+ "                                                            <br />\r\n"
				+ "                                                            <span style=\"color: #3c9691\">2021</span>  &copy <span style=\"color: #FF722D;\"></span>  | Le futur se construit\r\n"
				+ "                                                            aujourd'hui avec <span style=\"color: #FF722D;\">ACHRAF</span>\r\n"
				+ "                                                        </td>\r\n"
				+ "                                                    </tr>\r\n"
				+ "                                                </table>\r\n"
				+ "                                            </td>\r\n"
				+ "                                        </tr>\r\n"
				+ "                                    </table>\r\n" + "                                </td>\r\n"
				+ "                            </tr>\r\n" + "                        </table>\r\n"
				+ "                    </td>\r\n" + "                </tr>\r\n" + "            </table>\r\n"
				+ "        </td>\r\n" + "    </tr>\r\n" + "</table>\r\n" + "";
	}

}
