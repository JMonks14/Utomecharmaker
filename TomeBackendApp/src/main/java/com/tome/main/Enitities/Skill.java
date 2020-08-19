package com.tome.main.Enitities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="skills")
public class Skill {
	
	@Id
	@GeneratedValue
	private int skill_id;
	private String skill_name;
	private String description;
	private int fk_tree_id;
	private int prerequisite_1;
	private int prerequisite_2;
	private int prerequisite_3;
	private int prerequisite_4;
	private int prerequisite_5;
	private boolean is_multibuy;
	
//	@ManyToMany(mappedBy = "skills")
//	Set<Characters> characters;
	
	
	
	public int getSkill_id() {
		return skill_id;
	}
	public Skill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Skill(int skill_id, String skill_name, String description, int fk_tree_id, int prerequisite_1,
		int prerequisite_2, int prerequisite_3, int prerequisite_4, int prerequisite_5, boolean is_multibuy) {
	super();
	this.skill_id = skill_id;
	this.skill_name = skill_name;
	this.description = description;
	this.fk_tree_id = fk_tree_id;
	this.prerequisite_1 = prerequisite_1;
	this.prerequisite_2 = prerequisite_2;
	this.prerequisite_3 = prerequisite_3;
	this.prerequisite_4 = prerequisite_4;
	this.prerequisite_5 = prerequisite_5;
	this.is_multibuy = is_multibuy;
}
	public void setSkill_id(int skill_id) {
		this.skill_id = skill_id;
	}
	public String getSkill_name() {
		return skill_name;
	}
	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFk_tree_id() {
		return fk_tree_id;
	}
	public void setFk_tree_id(int fk_tree_id) {
		this.fk_tree_id = fk_tree_id;
	}
	public int getPrerequisite_1() {
		return prerequisite_1;
	}
	public void setPrerequisite_1(int prerequisite_1) {
		this.prerequisite_1 = prerequisite_1;
	}
	public int getPrerequisite_2() {
		return prerequisite_2;
	}
	public void setPrerequisite_2(int prerequisite_2) {
		this.prerequisite_2 = prerequisite_2;
	}
	public int getPrerequisite_3() {
		return prerequisite_3;
	}
	public void setPrerequisite_3(int prerequisite_3) {
		this.prerequisite_3 = prerequisite_3;
	}
	public int getPrerequisite_4() {
		return prerequisite_4;
	}
	public void setPrerequisite_4(int prerequisite_4) {
		this.prerequisite_4 = prerequisite_4;
	}
	public int getPrerequisite_5() {
		return prerequisite_5;
	}
	public void setPrerequisite_5(int prerequisite_5) {
		this.prerequisite_5 = prerequisite_5;
	}
	public boolean isIs_multibuy() {
		return is_multibuy;
	}
	public void setIs_multibuy(boolean is_multibuy) {
		this.is_multibuy = is_multibuy;
	}
	
	

}
