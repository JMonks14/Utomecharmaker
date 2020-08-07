package com.tome.main.Enitities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="characters")
public class Characters {
	
	@Id
	@GeneratedValue
	private int char_id;
	private String char_name;
	private String race;
	private String origin;
	private String char_background;
	private int fk_player_id;
	
	@Transient
	private boolean alive;
	@Transient
	private int HP;
	@Transient
	private int MP;
	@Transient
	private int AP_basic;
	@Transient
	private int AP_light;
	@Transient
	private int AP_heavy;
	@Transient
	private int AP_magic;
	@Transient
	private int XP_spent;
	
	public int getChar_id() {
		return char_id;
	}
	public void setChar_id(int char_id) {
		this.char_id = char_id;
	}
	public String getChar_name() {
		return char_name;
	}
	public void setChar_name(String char_name) {
		this.char_name = char_name;
	}
	public String getChar_background() {
		return char_background;
	}
	public void setChar_background(String char_background) {
		this.char_background = char_background;
	}
	
	public int getFk_player_id() {
		return fk_player_id;
	}
	public void setFk_player_id(int fk_player_id) {
		this.fk_player_id = fk_player_id;
	}

	public boolean getAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	
	public int getMP() {
		return MP;
	}
	public void setMP(int mP) {
		MP = mP;
	}
	public int getAP_basic() {
		return AP_basic;
	}
	public void setAP_basic(int aP_basic) {
		AP_basic = aP_basic;
	}
	public int getAP_light() {
		return AP_light;
	}
	public void setAP_light(int aP_light) {
		AP_light = aP_light;
	}
	public int getAP_heavy() {
		return AP_heavy;
	}
	public void setAP_heavy(int aP_heavy) {
		AP_heavy = aP_heavy;
	}
	public int getAP_magic() {
		return AP_magic;
	}
	public void setAP_magic(int aP_magic) {
		AP_magic = aP_magic;
	}
	public int getXP_spent() {
		return XP_spent;
	}
	public void setXP_spent(int xP_spent) {
		XP_spent = xP_spent;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	
}
