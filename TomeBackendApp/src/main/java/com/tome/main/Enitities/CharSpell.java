package com.tome.main.Enitities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="char_spells")
public class CharSpell {
	
	@Id
	@GeneratedValue
	private int line_id;
	private int fkCharId;
	private int fk_spell_id;
	
	public CharSpell(int fkCharId, int fk_spell_id) {
		super();
		this.fkCharId = fkCharId;
		this.fk_spell_id = fk_spell_id;
	}
	public CharSpell(int line_id, int fkCharId, int fk_spell_id) {
		super();
		this.line_id = line_id;
		this.fkCharId = fkCharId;
		this.fk_spell_id = fk_spell_id;
	}
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
	public int getFk_spell_id() {
		return fk_spell_id;
	}
	public void setFk_spell_id(int fk_spell_id) {
		this.fk_spell_id = fk_spell_id;
	}
	
	
	

}
