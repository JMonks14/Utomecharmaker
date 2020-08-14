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
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/buy")
	public ResponseEntity<CharSkills> buySkill(@RequestBody CharSkills skill){
		CharSkills buy = this.service.buySkill(skill);
		return new ResponseEntity<>(buy, HttpStatus.CREATED);
	}
	
	@DeleteMapping("reset/{id}")
	public void reset(@PathVariable int id) {
		  this.service.reset(id);
		
	}

}
