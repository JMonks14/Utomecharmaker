package com.tome.main.Enitities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	
	private boolean alive;
	@Column(columnDefinition="tinyint default 3")
	private int HP;
	@Column(columnDefinition="tinyint default 3")
	private int MP;
	@Column(columnDefinition="tinyint default 1")
	private int AP_basic;
	
	private int AP_light;
	
	private int AP_heavy;
	
	private int AP_magic;
	
	private int XP_spent;
	
	
	
public Characters(String char_name, String race, String origin, String char_background, int fk_player_id,
			boolean alive, int hP, int mP, int aP_basic, int aP_light, int aP_heavy, int aP_magic, int xP_spent) {
		super();
		this.char_name = char_name;
		this.race = race;
		this.origin = origin;
		this.char_background = char_background;
		this.fk_player_id = fk_player_id;
		this.alive = alive;
		HP = hP;
		MP = mP;
		AP_basic = aP_basic;
		AP_light = aP_light;
		AP_heavy = aP_heavy;
		AP_magic = aP_magic;
		XP_spent = xP_spent;
	}
public Characters(int char_id, String char_name, String race, String origin, String char_background,
			int fk_player_id, boolean alive, int hP, int mP, int aP_basic, int aP_light, int aP_heavy, int aP_magic,
			int xP_spent) {
		super();
		this.char_id = char_id;
		this.char_name = char_name;
		this.race = race;
		this.origin = origin;
		this.char_background = char_background;
		this.fk_player_id = fk_player_id;
		this.alive = alive;
		HP = hP;
		MP = mP;
		AP_basic = aP_basic;
		AP_light = aP_light;
		AP_heavy = aP_heavy;
		AP_magic = aP_magic;
		XP_spent = xP_spent;
	}
//	@ManyToMany
//	@JoinTable(
//	name="char_skills",
//	joinColumns= @JoinColumn(name="fk_char_id"),
//	inverseJoinColumns= @JoinColumn(name="fk_skill_id"))
//	Set<Skill> skills;
	
	
	
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
