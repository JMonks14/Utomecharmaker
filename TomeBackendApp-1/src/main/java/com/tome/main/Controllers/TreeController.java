package com.tome.main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tome.main.Enitities.Skill;
import com.tome.main.Enitities.SkillTree;
import com.tome.main.Services.SkillTreeServices;

@RestController
@CrossOrigin
@RequestMapping("/tree")
public class TreeController {

	@Autowired
	SkillTreeServices service;
	
	@GetMapping("/list")
	public List<SkillTree> listTrees() {
		return this.service.listTrees();
	}
	
	@GetMapping("/findbyid/{id}")
	public SkillTree findById(@PathVariable int id) {
		return this.service.findById(id);
	}
	
	@PostMapping("/create")
	public ResponseEntity<SkillTree> createTree(@RequestBody SkillTree tree) {
		SkillTree newTree = this.service.create(tree);
		return new ResponseEntity<>(newTree, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestBody SkillTree tree) {
		this.service.deleteTree(tree);
		return new ResponseEntity<>("Tree Deleted", HttpStatus.ACCEPTED);
	}
}
