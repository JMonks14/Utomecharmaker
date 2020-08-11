package com.tome.main.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tome.main.Enitities.CharSkills;
import com.tome.main.Repos.CharSkillRepo;

@Service
public class CharSkillServices {
	
	@Autowired
	CharSkillRepo repo;
	
	public List<CharSkills> charSkillIds(int char_id) {
		return this.repo.getCharSkillIds(char_id);
	}
	
	public CharSkills buySkill(CharSkills skill) {
		return this.repo.save(skill);
	}

}
