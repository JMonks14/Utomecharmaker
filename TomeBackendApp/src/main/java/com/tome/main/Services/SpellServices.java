package com.tome.main.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tome.main.Enitities.Skill;
import com.tome.main.Enitities.Spell;
import com.tome.main.Exceptions.SpellNotFoundException;
import com.tome.main.Repos.SpellRepo;

@Service
public class SpellServices {
	
	@Autowired
	SpellRepo repo;
	
	public List<Spell> listSpells() {
		return this.repo.listAll();
	}
	
	public Spell findById(int id) {
		return this.repo.findById(id).orElseThrow(SpellNotFoundException::new);
	}
	
	public Spell buy(Spell bought) {
		Spell spell = findById(bought.getSpell_id());
		spell.setSpellchars(bought.getSpellchars());
		return this.repo.save(spell);
	}
//	public void buySpell(int char_id, int spell_id) {
//		this.repo.buySpell(char_id, spell_id);
//	}
	

}
