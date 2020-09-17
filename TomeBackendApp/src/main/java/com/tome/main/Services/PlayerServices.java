package com.tome.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tome.main.Enitities.Player;
import com.tome.main.Exceptions.PlayerAlreadyExistsException;
import com.tome.main.Exceptions.PlayerNotFoundException;
import com.tome.main.Repos.PlayerRepo;
import com.tome.main.security.PlayerDTO;

import src.main.java.com.baeldung.service.String;
import src.main.java.com.baeldung.service.UserAlreadyExistException;

@Service
public class PlayerServices {

	@Autowired
	PlayerRepo repo;
	
	@Autowired
	PasswordEncoder encoder;
	
	public PlayerServices(PlayerRepo repo) {
		super();
		this.repo=repo;
	}
	
	private boolean emailExists(final String email) {
        return repo.findByEmail(email) != null;
    }
	
	public Player create(PlayerDTO player) throws PlayerAlreadyExistsException {
		
		if (emailExists(player.getEmail())) {
            throw new PlayerAlreadyExistsException();
        }
		
		if (repo.findByUsername(player.getUsername()).isPresent()) {
			throw new PlayerAlreadyExistsException();
		}
		
		Player newp = new Player();
		newp.setFirst_name(player.getFirstName());
		newp.setLast_name(player.getLastName());
		newp.setUsername(player.getUsername());
		newp.setPassword(encoder.encode(player.getPassword()));
		newp.setEmail(player.getEmail());
		newp.setRole("ROLE_USER");
		Player saved = this.repo.save(newp);
		return saved;
	}
	
	public Player viewById(int id) {
		Player found = this.repo.findById(id).orElseThrow(PlayerNotFoundException::new);
		return found;
	}
	public Player viewLatest() {
		Player latest = this.repo.findLatest();
		return latest;
	}
	
	public Player update(Player newPlayer, int id) {
		try {
			Player player = viewById(id);
		if (newPlayer.getFirst_name()!=null)
		player.setFirst_name(newPlayer.getFirst_name());
		if (newPlayer.getLast_name()!=null)
		player.setLast_name(newPlayer.getLast_name());
		if (newPlayer.getUsername()!=null)
		player.setUsername(newPlayer.getUsername());
		if (newPlayer.getPassword()!=null)
		player.setPassword(newPlayer.getPassword());
		if (newPlayer.getActiveChar()!=0)
		player.setActiveChar(newPlayer.getActiveChar());
		Player saved = this.repo.save(player);
		return saved;
		}
		catch (PlayerNotFoundException e) {
			return null;
		}
		
	}
	public Player findByUsername(String username) {
		return this.repo.findByUsername(username).orElseThrow(PlayerNotFoundException::new);
	}

}
