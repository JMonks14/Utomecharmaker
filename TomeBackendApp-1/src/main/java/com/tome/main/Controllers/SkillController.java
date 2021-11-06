package com.tome.main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tome.main.Enitities.Player;
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
	
	@PostMapping(value = "/create")
	public ResponseEntity<Skill> create(@RequestBody Skill skill) {
		Skill newSkill = service.create(skill);
		return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/buyskill")
	public ResponseEntity<Skill> buy(@RequestBody Skill skill) {
		Skill upSkill = service.buy(skill);
		return new ResponseEntity<>(upSkill, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value="/delete")
	public ResponseEntity<String> delete(@RequestBody Skill skill) {
		this.service.delete(skill);
		return new ResponseEntity<>("Skill Deleted", HttpStatus.ACCEPTED);
	}
	
//	@GetMapping(value="/findbychar/{id}")
//	public List<Skill> getCharSkills(@PathVariable int id) {
//		return this.service.getCharSkills(id);
//	}

}
