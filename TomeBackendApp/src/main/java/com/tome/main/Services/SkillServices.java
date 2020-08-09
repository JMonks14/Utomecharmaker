package com.tome.main.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tome.main.Enitities.Skill;
import com.tome.main.Repos.SkillRepo;

@Service
public class SkillServices {
	
	@Autowired
	SkillRepo repo;
	
	public List<Skill> skillsByTreeId(int tree_id) {
		return this.repo.findByTreeId(tree_id);
	}

}