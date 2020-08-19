package com.tome.main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tome.main.Enitities.Spell;
import com.tome.main.Services.SpellServices;

@RestController
@CrossOrigin
@RequestMapping("/spell")
public class SpellController {
	
	@Autowired
	SpellServices service;
	
	@GetMapping("/listall")
	public List<Spell> listAll() {
		return this.service.listSpells();
	}
	
	@GetMapping("/find/{id}")
	public Spell findById(@PathVariable int id) {
		return this.service.findById(id);
	}
	
	@GetMapping(value="/findbychar/{id}")
	public List<Spell> getCharSpells(@PathVariable int id) {
		return this.service.getCharSpells(id);
	}

}
