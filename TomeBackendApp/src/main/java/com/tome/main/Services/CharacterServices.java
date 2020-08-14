package com.tome.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tome.main.Enitities.Characters;
import com.tome.main.Enitities.Player;
import com.tome.main.Exceptions.CharacterNotFoundException;
import com.tome.main.Repos.CharacterRepo;

@Service
public class CharacterServices {
	
	@Autowired
	CharacterRepo repo;
	
	@Autowired
	PlayerServices playerservice;
	
	public Characters create(Characters Char) {
		Player player = this.playerservice.viewById(Char.getFk_player_id());
		Characters saved = this.repo.save(Char);
		player.setActiveChar(saved.getChar_id());
		this.playerservice.update(player, player.getPlayer_id());
		return saved;
	}
	
	public Characters viewById(int id) {
		Characters found = this.repo.findById(id).orElseThrow(CharacterNotFoundException::new);
		return found;
	}
	
	public Characters update(Characters newChar, int id) {
		try {
			Characters Char = viewById(id);
		Char.setAP_basic(newChar.getAP_basic());
		Char.setAP_light(newChar.getAP_light());
		Char.setAP_heavy(newChar.getAP_heavy());
		Char.setAP_magic(newChar.getAP_magic());
		Char.setHP(newChar.getHP());
		Char.setMP(newChar.getMP());
		Char.setAlive(newChar.getAlive());
		Char.setXP_spent(newChar.getXP_spent());
		Characters saved = this.repo.save(Char);
		return saved;
		}
		catch (CharacterNotFoundException e) {
			return null;
		}
		
	}
	public Characters kill(int id) {
		Characters dead = viewById(id);
		dead.setAlive(false);
		Player player = this.playerservice.viewById(dead.getFk_player_id());
		player.setActiveChar(0);
		this.playerservice.update(player, player.getPlayer_id());
		return this.update(dead, id);
	}

}
