package com.api.mail.manag.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.api.mail.manag.dao.InfoRepository;
import com.api.mail.manag.entity.Info;

public class InfoServiceImpl implements InfoService {

	@Autowired
	private InfoRepository infoRepository;

	// Page'interface' de SpringDataJpa qui fournit qui fournit des attribues
	@Override
	public Page<Info> getInfos(short page, short size) {

		// (+) pour créer une Pageable instance; on l'utiliser l'PageRequest
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
		return infoRepository.findAll(pageable);
	}

	@Override
	public Info getInfoById(Long id) {

		Optional<Info> info = infoRepository.findById(id);

		if (info.isPresent())
			return info.get();

		throw new RuntimeException("le Info n'existe pas pour Id : " + id);
	}

}
