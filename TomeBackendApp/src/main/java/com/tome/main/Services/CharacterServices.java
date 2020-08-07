package com.tome.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tome.main.Enitities.Characters;
import com.tome.main.Repos.CharacterRepo;

@Service
public class CharacterServices {
	
	@Autowired
	CharacterRepo repo;
	
	public Characters create(Characters Char) {
		return this.repo.save(Char);
	}

}
