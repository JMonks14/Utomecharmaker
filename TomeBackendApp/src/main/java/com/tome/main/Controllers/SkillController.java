package com.tome.main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tome.main.Enitities.CharSkillGroup;
import com.tome.main.Enitities.Skill;
import com.tome.main.Services.SkillServices;

@RestController
@CrossOrigin
@RequestMapping("/skills")
public class SkillController {
	
	@Autowired
	SkillServices service;
	
	@GetMapping(value="/listbytree/{tree_id}")
	public List<Skill> listByTree(@PathVariable int tree_id) {
		return this.service.skillsByTreeId(tree_id);
	}
	
	@GetMapping(value="/findbyid/{id}")
	public Skill findById(@PathVariable int id) {
		return this.service.findById(id);
	}
	
	@GetMapping(value="/findbychar/{id}")
	public List<Skill> getCharSkills(@PathVariable int id) {
		return this.service.getCharSkills(id);
	}

}
