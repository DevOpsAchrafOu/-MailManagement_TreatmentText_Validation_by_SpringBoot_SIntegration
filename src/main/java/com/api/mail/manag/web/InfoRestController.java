package com.api.mail.manag.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.mail.manag.entity.Info;
import com.api.mail.manag.service.InfoService;

@RestController
@RequestMapping("/api/infos")
@CrossOrigin("*")
public class InfoRestController {

	@Autowired
	private InfoService infService;

	/** -------------------- Get All With Pagination -------------------- **/
	// get http://localhost:8080/api/infos
	// or get http://localhost:8080/api/infos?page=1&size=10
	@GetMapping() // par défaul page=0 et size=10
	public Page<Info> getAllDemands(@RequestParam(defaultValue = "0") short currentPage,
			@RequestParam(defaultValue = "10") short size) {
		return infService.getInfos(currentPage, size);
	}

	/** ----------------------- Get By Id ----------------------- **/
	// get http://localhost:8080/api/infos/2
	@GetMapping(value = "/{id}")
	public Info getDemandById(@PathVariable Long id) {
		return infService.getInfoById(id);
	}

}
