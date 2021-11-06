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
	
	public Skill findById(int id) {
		return this.repo.findById(id).orElseThrow(SkillNotFoundException::new);
	}
	
	public Skill create(Skill skill) {
		return this.repo.save(skill);
	}
	
	public Skill buy(Skill bought) {
		Skill skill = findById(bought.getSkill_id());
		skill.setSkillchars(bought.getSkillchars());
		return this.repo.save(skill);
	}
	public void delete(Skill skill) {
		this.repo.delete(skill);
	}
}
