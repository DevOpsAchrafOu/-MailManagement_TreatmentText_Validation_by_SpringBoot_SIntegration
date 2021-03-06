package com.api.mail.manag.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.api.mail.manag.entity.Info;

@Repository
public interface InfoRepository {

	// Page'interface' de SpringDataJpa qui fournit qui fournit des attribues
	Page<Info> findAll(Pageable pageable);

	/*
	 * return Optional( signifier on peut return certain champ ou object lui-m)
	 * optional est return objet ou null et pas exception
	 */
	Optional<Info> findById(Long id);

}
