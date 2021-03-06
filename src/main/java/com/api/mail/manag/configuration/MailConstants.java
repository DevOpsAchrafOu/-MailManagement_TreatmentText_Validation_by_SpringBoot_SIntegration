package com.api.mail.manag.configuration;

public class MailConstants {

	/*
	 * les paramètres de configuration de mail
	 */

	/*
	 * protocole IMAP est plus moderne que POP3 et permettant de traité avant
	 * recevoir dans notre BackEnd par contre POP3 ;il doit télécharger dans notre
	 * serveur ou progarame de email et puis traité.
	 */
	public static final String PROTOCOL = "imap";
	public static final String HOST = "imap-mail.outlook.com";
	public static final String PORT = "993";

	public static final String USERNAME = "achraf.spring@outlook.com";
	public static final String PASSWORD = "azmtjjzlixfujsvs";

}
