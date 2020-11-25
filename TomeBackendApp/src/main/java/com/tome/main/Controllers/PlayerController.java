package com.tome.main.Controllers;

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

import com.tome.main.Enitities.Player;
import com.tome.main.Services.PlayerServices;

@RestController
@RequestMapping("/player")
@CrossOrigin
public class PlayerController {
	
	@Autowired
	PlayerServices service;
	
	@PostMapping("/reg")
	public ResponseEntity<Player> register(@RequestBody Player player) {
		Player saved = this.service.create(player);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/view/{id}")
	public Player view(@PathVariable int id) { 
		return this.service.viewById(id);
	}
	
	@GetMapping("/viewlatest")
	public Player viewLatest() {
		return this.service.viewLatest();
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<Player> update(@RequestBody Player newPlayer, @PathVariable int id) {
		Player updated = this.service.update(newPlayer, id);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}
	@GetMapping("/find/{username}")
	public Player findByUsername(@PathVariable String username) {
		return this.service.findByUsername(username);
	}

}
