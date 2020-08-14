package com.tome.main.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tome.main.Enitities.CharSpell;
import com.tome.main.Repos.CharSpellRepo;

@Service
public class CharSpellServices {
	
	@Autowired
	CharSpellRepo repo;
	
	public List<CharSpell> charSpellIds(int char_id) {
		return this.repo.getCharSpellIds(char_id);
	}
	
	public CharSpell buySpell(CharSpell spell) {
		return this.repo.save(spell);
	}
	public void reset(int char_id) {
		 this.repo.reset(char_id);
	}

}
