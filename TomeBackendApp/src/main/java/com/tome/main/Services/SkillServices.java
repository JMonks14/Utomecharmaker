package com.tome.main.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tome.main.Enitities.Skill;
import com.tome.main.Exceptions.SkillNotFoundException;
import com.tome.main.Repos.SkillRepo;

@Service
public class SkillServices {
	
	@Autowired
	SkillRepo repo;
	
	public List<Skill> skillsByTreeId(int tree_id) {
		return this.repo.findByTreeId(tree_id);
	}
	
	public void buySkill(int char_id, int skill_id) {
		this.buySkill(char_id, skill_id);
	}
	
	public Skill findById(int id) {
		return this.repo.findById(id).orElseThrow(SkillNotFoundException::new);
	}

}
