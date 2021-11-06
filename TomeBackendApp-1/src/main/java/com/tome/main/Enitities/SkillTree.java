package com.tome.main.Enitities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="skill_trees")
public class SkillTree {

	@Id
	@GeneratedValue
	private int tree_id;
	private String tree_name;
	
	@OneToMany(mappedBy = "tree")
	private List<Skill> skills;
	
	public int getTree_id() {
		return tree_id;
	}
	public void setTree_id(int tree_id) {
		this.tree_id = tree_id;
	}
	public String getTree_name() {
		return tree_name;
	}
	public void setTree_name(String tree_name) {
		this.tree_name = tree_name;
	}
	public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
	
	
}
