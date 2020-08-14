package com.tome.main.Enitities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="char_skills")
public class CharSkills {
	
	@Id
	@GeneratedValue
	private int line_id;
	private int fkCharId;
	private int fk_skill_id;
	
	public int getLine_id() {
		return line_id;
	}
	public void setLine_id(int line_id) {
		this.line_id = line_id;
	}
	public int getFk_char_id() {
		return fkCharId;
	}
	public void setFk_char_id(int fk_char_id) {
		this.fkCharId = fk_char_id;
	}
	public int getFk_skill_id() {
		return fk_skill_id;
	}
	public void setFk_skill_id(int fk_skill_id) {
		this.fk_skill_id = fk_skill_id;
	}
	
	

}