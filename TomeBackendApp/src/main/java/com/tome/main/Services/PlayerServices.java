package com.tome.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tome.main.Enitities.Player;
import com.tome.main.Exceptions.PlayerNotFoundException;
import com.tome.main.Repos.PlayerRepo;

@Service
public class PlayerServices {

	@Autowired
	PlayerRepo repo;
	
	public PlayerServices(PlayerRepo repo) {
		super();
		this.repo=repo;
	}
	
	public Player create(Player player) {
		Player saved = this.repo.save(player);
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
