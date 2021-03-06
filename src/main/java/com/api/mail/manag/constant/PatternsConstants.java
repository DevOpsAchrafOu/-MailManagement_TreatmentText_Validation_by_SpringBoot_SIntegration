package com.api.mail.manag.constant;

public class PatternsConstants {

	/*
	 * les Patterns pour filter les Clés des e-mails (d'ailleurs; chaque ligne est
	 * sous-forme clé/valeur)
	 */

	public static final String title = "^([tT][iI][tT][lL][eE])(( )?(  )?:( )?).*";
	public static final String firstName = ".*((([fF][iI][rR][sS][tT])\\b)\\b.*([nN][aA][mM][eE]))\\b.*(\\b( )?(  )?:( )?).*";
	public static final String lastName = ".*((([lL][aA][sS][tT])\\b)\\b.*([nN][aA][mM][eE]))\\b.*(\\b( )?(  )?:( )?).*";
	public static final String phone = "^([pP][hH][oO][nN][eE])(( )?(  )?:( )?).*";
	// (+)client salarié ou non
	public static final String employee = "^([eE][mM][pP][lL][oO][yY][eE][eE])(( )?(  )?:( )?).*";

}
