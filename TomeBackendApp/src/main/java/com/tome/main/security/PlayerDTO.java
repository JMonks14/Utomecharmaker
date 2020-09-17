package com.tome.main.security;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;
import com.tome.main.security.validation.PasswordMatches;
import com.tome.main.security.validation.ValidEmail;

@PasswordMatches
public class PlayerDTO {
	
	@NotNull
	@NotEmpty
    private String firstName;
    
    @NotNull
    @NotEmpty
    private String lastName;
    
    @NotNull
    @NotEmpty
    private String username;
    
    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;
    
    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    
}
