package com.api.mail.manag.service;

import org.springframework.data.domain.Page;

import com.api.mail.manag.entity.Info;

public interface InfoService {

	// Page'interface' de SpringDataJpa qui fournit qui fournit des attribues
	public Page<Info> getInfos(short page, short size);

	public Info getInfoById(Long id);

}
