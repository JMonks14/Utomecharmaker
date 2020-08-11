package com.tome.main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tome.main.Enitities.CharSkills;
import com.tome.main.Services.CharSkillServices;

@RestController
@CrossOrigin
@RequestMapping("/charskills")
public class CharSkillController {
	
	@Autowired
	CharSkillServices service;
	
	@GetMapping("/byid/{id}")
	public List<CharSkills> charSkillIds(@PathVariable int id) {
		return this.service.charSkillIds(id);
	}

}
