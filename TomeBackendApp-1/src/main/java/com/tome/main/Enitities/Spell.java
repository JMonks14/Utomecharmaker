package com.tome.main.Enitities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="spells")
public class Spell {
	
	@Id
	@GeneratedValue
	private int spell_id;
	private String spell_name;
	private int prerequisite;
	private int mana_cost;
	private String type;
	private String description;
	
	
	@ManyToMany(mappedBy="spells")
	@JsonIgnore
	private List<Characters> spellchars;
	
	
	public Spell() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Spell(String spell_name, int prerequisite, int mana_cost, String type, String description) {
		super();
		this.spell_name = spell_name;
		this.prerequisite = prerequisite;
		this.mana_cost = mana_cost;
		this.type = type;
		this.description = description;
	}
	
	
	public Spell(int spell_id, String spell_name, int prerequisite, int mana_cost, String type, String description) {
		super();
		this.spell_id = spell_id;
		this.spell_name = spell_name;
		this.prerequisite = prerequisite;
		this.mana_cost = mana_cost;
		this.type = type;
		this.description = description;
	}


	public int getSpell_id() {
		return spell_id;
	}
	public void setSpell_id(int spell_id) {
		this.spell_id = spell_id;
	}
	public String getSpell_name() {
		return spell_name;
	}
	public void setSpell_name(String spell_name) {
		this.spell_name = spell_name;
	}
	public int getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(int prerequisite) {
		this.prerequisite = prerequisite;
	}
	public int getMana_cost() {
		return mana_cost;
	}
	public void setMana_cost(int mana_cost) {
		this.mana_cost = mana_cost;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Characters> getSpellchars() {
		return spellchars;
	}

	public void setSpellchars(List<Characters> spellchars) {
		this.spellchars = spellchars;
	}
	
	

}
