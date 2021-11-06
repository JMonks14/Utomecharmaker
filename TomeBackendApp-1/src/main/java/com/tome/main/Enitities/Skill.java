package com.tome.main.Enitities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="skills")
public class Skill {
	
	@Id
	@GeneratedValue
	private int skill_id;
	private String skill_name;
	private String description;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="fk_tree_id")
	private SkillTree tree;
	private int prerequisite_1;
	private int prerequisite_2;
	private int prerequisite_3;
	private int prerequisite_4;
	private int prerequisite_5;
	private boolean is_multibuy;
	@Column(name="plusHP")
	private int plusHP;
	
	@Column(name="plusFP")
	private int plusFP;
	
	@Column(name="plusAPlight")
	private int plusAPlight;
	
	@Column(name="plusAPheavy")
	private int plusAPheavy;
	
	@Column(name="plusAPmagic")
	private int plusAPmagic;
	
	@ManyToMany(mappedBy="skills")
	@JsonIgnore
	private List<Characters> skillchars;
	
	public int getSkill_id() {
		return skill_id;
	}
	public Skill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Skill(int skill_id, String skill_name, String description, SkillTree tree, int prerequisite_1,
		int prerequisite_2, int prerequisite_3, int prerequisite_4, int prerequisite_5, boolean is_multibuy) {
	super();
	this.skill_id = skill_id;
	this.skill_name = skill_name;
	this.description = description;
	this.tree = tree;
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
	
	public SkillTree getTree() {
		return tree;
	}
	public void setTree(SkillTree tree) {
		this.tree = tree;
	}
	public List<Characters> getSkillchars() {
		return skillchars;
	}
	public void setSkillchars(List<Characters> skillchars) {
		this.skillchars = skillchars;
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
	public int getPlusHP() {
		return plusHP;
	}
	public void setPlusHP(int plusHP) {
		this.plusHP = plusHP;
	}
	public int getPlusFP() {
		return plusFP;
	}
	public void setPlusFP(int plusFP) {
		this.plusFP = plusFP;
	}
	public int getPlusAPlight() {
		return plusAPlight;
	}
	public void setPlusAPlight(int plusAPlight) {
		this.plusAPlight = plusAPlight;
	}
	public int getPlusAPheavy() {
		return plusAPheavy;
	}
	public void setPlusAPheavy(int plusAPheavy) {
		this.plusAPheavy = plusAPheavy;
	}
	public int getPlusAPmagic() {
		return plusAPmagic;
	}
	public void setPlusAPmagic(int plusAPmagic) {
		this.plusAPmagic = plusAPmagic;
	}
	
	

}
