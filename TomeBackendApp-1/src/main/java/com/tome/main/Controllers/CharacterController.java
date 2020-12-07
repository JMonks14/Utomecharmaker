package com.tome.main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tome.main.Enitities.Characters;
import com.tome.main.Enitities.Skill;
import com.tome.main.Services.CharacterServices;

@RestController
@RequestMapping("/character")
@CrossOrigin
public class CharacterController {
	
	@Autowired
	CharacterServices service;
	
	@PostMapping("/create")
	public ResponseEntity<Characters> createChar(@RequestBody Characters Char) {
		Characters newChar = this.service.create(Char);
		return new ResponseEntity<>(newChar, HttpStatus.CREATED);
	}
	
	@GetMapping("/view/{id}")
	public Characters view(@PathVariable int id) { 
		return this.service.viewById(id);
	}
	
	
	@PutMapping("/kill/{id}")
	public ResponseEntity<Characters> kill(@PathVariable int id) {
		Characters killed = this.service.kill(id);
		return new ResponseEntity<>(killed, HttpStatus.ACCEPTED);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Characters> update(@RequestBody Characters newChar, @PathVariable int id) {
		Characters updated = this.service.update(newChar, id);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/list_alive")
	public List<Characters> listAlive() {
		return this.service.listAlive();
	}

}
