package com.tome.main.Enitities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="players")
public class Player {

	@Id
	@GeneratedValue
	private int player_id;
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	private int activechar_id;
	
		public Player(String first_name, String last_name, String username, String password, int activechar_id) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.activechar_id = activechar_id;
	}
		
		
	public Player(int player_id, String first_name, String last_name, String username, String password,
				int activechar_id) {
			super();
			this.player_id = player_id;
			this.first_name = first_name;
			this.last_name = last_name;
			this.username = username;
			this.password = password;
			this.activechar_id = activechar_id;
		}


	public int getActiveChar() {
		return activechar_id;
	}
	public void setActiveChar(int fk_activeChar_id) {
		this.activechar_id = fk_activeChar_id;
	}
	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
