package com.tome.main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tome.main.Enitities.Characters;
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

}
