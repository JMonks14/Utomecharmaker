package com.tome.main.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tome.main.Enitities.Player;
import com.tome.main.Exceptions.PlayerAlreadyExistsException;
import com.tome.main.Services.PlayerServices;
import com.tome.main.security.TomeUserDetails;

@RestController
@RequestMapping("/player")
@CrossOrigin
public class PlayerController {
	
	@Autowired
	PlayerServices service;
	
	@PostMapping("/reg")
	public ResponseEntity<String> register(@RequestBody Player player) {
		try {
			this.service.create(player);
			return new ResponseEntity<>("Account Successfully Created", HttpStatus.CREATED);
		} catch (PlayerAlreadyExistsException ex){
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/current")
	@ResponseBody
	public String getCurrentUser(Principal principal) {
		return principal.getName();
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
	public ResponseEntity<Player> update(@RequestBody Player newPlayer, @PathVariable int id, @RequestHeader("passcheck") String pass) {
		if (service.passwordCorrect(pass, id)) {
			Player updated = this.service.update(newPlayer, id);
			TomeUserDetails newDetails = new TomeUserDetails(updated);
			Authentication auth = new UsernamePasswordAuthenticationToken(newDetails, newDetails.getPassword(), newDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
		}
		else {	
			return new ResponseEntity<>(newPlayer, HttpStatus.UNAUTHORIZED);
		}
	}
	@GetMapping("/find/{username}")
	public Player findByUsername(@PathVariable String username) {
		return this.service.findByUsername(username);
	}
	
	@PostMapping("/updatePassword/{id}")
	public ResponseEntity<Player> updatePassword(@RequestBody Player newPlayer, @PathVariable int id, @RequestHeader("passcheck") String pass) {
		if (service.passwordCorrect(pass, id)) {
			service.updatePassword(newPlayer, newPlayer.getPassword());
			TomeUserDetails newDetails = new TomeUserDetails(service.viewById(id));
			Authentication auth = new UsernamePasswordAuthenticationToken(newDetails, newDetails.getPassword(), newDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			return new ResponseEntity<>(service.viewById(id), HttpStatus.ACCEPTED);
		}
		else {	
			return new ResponseEntity<>(newPlayer, HttpStatus.UNAUTHORIZED);
		}
	}

}
