package com.api.mail.manag.service;

import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.message1;
import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.message2;
import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.message22;
import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.message3;
import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.message33;
import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.message333;
import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.message4;
import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.styleBR;
import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.styleEtoil;
import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.v_firstName;
import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.v_lastName;
import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.v_phone;
import static com.api.mail.manag.constant.ErrorMessagesAndInterfaceConstants.v_title;

import org.springframework.stereotype.Service;

import com.api.mail.manag.entity.Info;

@Service
public class ValidationFieldsOfInfoServiceImpl implements ValidationFieldsOfInfoService {

	// rassembler les messages d'erreur de validation de chaque champ non valide
	public String errorMessagesOfEachInvalidField(Info info) {

		String messages = "", m_isRequired = "", m_checkLength4 = "", m_checkLength10 = "", m_isPhone = "";
		String m_isOnlyCharactors = "", m_isOnlyCharactorsNumbres = "", m_isOnlyCharactorsAndOrNumbres = "";

		// (+) title
		if (!isRequired(info.getTitle())) {
			m_isRequired += v_title;
		} else {
			if (!checkLength(info.getTitle(), 4)) {
				m_checkLength4 += v_title;
			}
			if (!isOnlyCharactorsAndOrNumbres(info.getTitle())) {
				m_isOnlyCharactorsAndOrNumbres += v_title;
			}

		}

		// (+) prénom
		if (!isRequired(info.getFirstName())) {
			m_isRequired += v_firstName;
		} else {
			if (!checkLength(info.getFirstName(), 4)) {
				m_checkLength4 += v_firstName;
			}
			if (!isOnlyCharactors(info.getFirstName())) {
				m_isOnlyCharactors += v_firstName;
			}

		}

		// (+) nom
		if (!isRequired(info.getLastName())) {
			m_isRequired += v_lastName;
		} else {
			if (!checkLength(info.getLastName(), 4)) {
				m_checkLength4 += v_lastName;
			}
			if (!isOnlyCharactors(info.getLastName())) {
				m_isOnlyCharactors += v_lastName;
			}

		}

		// (+) phone
		if (!isRequired(info.getPhone())) {
			m_isRequired += v_phone;
		} else {
			if (!checkLength(info.getPhone(), 10)) {
				m_checkLength10 += v_phone;
			}
			if (!isPhone(info.getPhone())) {
				m_isPhone += v_phone;
			}

		}

		// (+) test si il y a un champ pas valider
		if (!m_isRequired.isEmpty())
			m_isRequired = styleEtoil + deleteLastVirgule(m_isRequired += message1) + styleBR;

		if (!m_checkLength4.isEmpty())
			m_checkLength4 = styleEtoil + deleteLastVirgule(m_checkLength4 += message2) + styleBR;

		if (!m_checkLength10.isEmpty())
			m_checkLength10 = styleEtoil + message22 + styleBR;

		if (!m_isOnlyCharactors.isEmpty())
			m_isOnlyCharactors = styleEtoil + deleteLastVirgule(m_isOnlyCharactors += message3) + styleBR;

		if (!m_isOnlyCharactorsAndOrNumbres.isEmpty())
			m_isOnlyCharactorsAndOrNumbres = styleEtoil + deleteLastVirgule(m_isOnlyCharactorsAndOrNumbres += message33)
					+ styleBR;

		if (!m_isOnlyCharactorsNumbres.isEmpty()) {
			m_isOnlyCharactorsNumbres = styleEtoil + deleteLastVirgule(m_isOnlyCharactorsNumbres += message333)
					+ styleBR;
		}

		if (!m_isPhone.isEmpty())
			m_isPhone = styleEtoil + message4 + styleBR;

		// (+) resembles les messages du validation
		messages = m_isRequired + m_checkLength4 + m_checkLength10 + m_isOnlyCharactorsAndOrNumbres;
		messages += m_isOnlyCharactorsNumbres + m_isOnlyCharactors + m_isPhone;

		return messages;
	}

	/** --------------------- Sous-fonction ----------------------- **/
//	validate field is required
	public boolean isRequired(String field) {

		return field != null && !field.trim().isEmpty();// not null or empty
	}

//	validate field is Only Charactors
	public boolean isOnlyCharactors(String field) {

		return field.trim().matches("^[a-zA-Z\\s]+");
	}

//	peut contient des chiffres et des caractères(ou uniquement des caractères)
	public boolean isOnlyCharactorsAndOrNumbres(String field) {

		return field.trim().matches("^[a-zA-Z0-9\\s\\.]+");
	}

//	peut contient des chiffres et des caractères
//	public boolean isOnlyCharactorsNumbres(String field) {
//
//		return field.trim().matches("(?!^[0-9\\s]*$)(?!^[a-zA-Z\\s]*$)^([a-zA-Z0-9\\s]{1,})$");
//	}

	public boolean isPhone(String field) {

		return field.trim().matches("^0[5-7][0-9]{4,}");
	}

	public boolean checkLength(String field, int len) {

		return field.trim().length() >= len;
	}

	//
	public String deleteLastVirgule(String mot) {
		// (+) pour détruire la dérinier Virgule dans le message
		StringBuilder sb = new StringBuilder(mot);
		if (sb.indexOf(",") != -1) {
			int len = sb.lastIndexOf(",");
			sb.replace(len, len + 1, "");
		}

		return sb.toString();
	}

}
