package com.tome.main.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tome.main.Enitities.Characters;
import com.tome.main.Enitities.Player;
import com.tome.main.Enitities.Skill;
import com.tome.main.Exceptions.CharacterNotFoundException;
import com.tome.main.Repos.CharacterRepo;

@Service
public class CharacterServices {
	
	@Autowired
	CharacterRepo repo;
	
	@Autowired
	PlayerServices playerservice;
	
	public Characters create(Characters Char) {
		Player player = this.playerservice.viewById(Char.getPlayer().getPlayer_id());
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
		Char.setSkills(newChar.getSkills());
		Char.setSpells(newChar.getSpells());
		return this.repo.save(Char);
		
		}
		catch (CharacterNotFoundException e) {
			return null;
		}
		
	}
	public Characters kill(int id) {
		Characters dead = viewById(id);
		dead.setAlive(false);
		Player player = this.playerservice.viewById(dead.getPlayer().getPlayer_id());
		player.setActiveChar(0);
		this.playerservice.update(player, player.getPlayer_id());
		return this.update(dead, id);
	}
	
	public List<Characters> listAlive() {
		List<Characters> allChars = this.repo.findAll();
		List<Characters> alive = allChars.stream()
									.filter(Char -> Char.getAlive()==true)
									.collect(Collectors.toList());
		return alive;
	}

}
