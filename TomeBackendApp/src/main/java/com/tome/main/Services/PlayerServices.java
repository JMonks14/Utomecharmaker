package com.tome.main.Services;

import java.util.Optional;

import com.tome.main.Enitities.Player;
import com.tome.main.Exceptions.PlayerNotFoundException;
import com.tome.main.Repos.PlayerRepo;

public class PlayerServices {
	
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
	
	public Player update(Player newPlayer, int id) {
		try {
			Player player = viewById(id);
		newPlayer.setPlayer_id(id);
		Player saved = this.repo.save(newPlayer);
		return saved;
		}
		catch (PlayerNotFoundException e) {
			return null;
		}
		
	}

}
