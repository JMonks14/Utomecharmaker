package com.tome.main.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tome.main.Enitities.SkillTree;
import com.tome.main.Exceptions.TreeNotFoundException;
import com.tome.main.Repos.SkillTreeRepo;

@Service
public class SkillTreeServices {

	@Autowired
	SkillTreeRepo repo;
	
	public List<SkillTree> listTrees() {
		return this.repo.findAll();
	}
	
	public SkillTree create(SkillTree tree) {
		return this.repo.save(tree);
	}
	
	public void deleteTree(SkillTree tree) {
		this.repo.delete(tree);
	}
	
	public SkillTree findById (int id) {
		return this.repo.findById(id).orElseThrow(TreeNotFoundException::new);
	}
}
