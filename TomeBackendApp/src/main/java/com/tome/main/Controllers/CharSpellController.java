package com.tome.main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tome.main.Enitities.CharSpell;
import com.tome.main.Services.CharSpellServices;

@RestController
@CrossOrigin
@RequestMapping("/charspell")
public class CharSpellController {
	
	@Autowired
	CharSpellServices service;
	
	@GetMapping("/byid/{id}")
	public List<CharSpell> charSpellIds(@PathVariable int id) {
		return this.service.charSpellIds(id);
	}
	
	@PostMapping("/buy")
	public ResponseEntity<CharSpell> buySpell(@RequestBody CharSpell spell){
		CharSpell buy = this.service.buySpell(spell);
		return new ResponseEntity<>(buy, HttpStatus.CREATED);
	}

}
