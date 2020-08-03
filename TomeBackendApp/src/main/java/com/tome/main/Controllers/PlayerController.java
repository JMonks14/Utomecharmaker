package com.tome.main.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.persistence.domain.Card;
import com.tome.main.Enitities.Player;
import com.tome.main.Services.PlayerServices;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	PlayerServices service;
	
	@PostMapping("/register")
	public ResponseEntity<Player> create(@RequestBody Player player) {
		Player registered = service.create(player);
		return new ResponseEntity<>(registered, HttpStatus.CREATED);
	}
	
	@GetMapping("/view/{id}")
	public Player view(@PathVariable int id) { 
		return this.service.viewById(id);
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<Player> update(@RequestBody Player newPlayer, @PathVariable Long id) {
		Player updated = this.service.update(newPlayer, id);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}

}
