package com.tome.main.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tome.main.Enitities.Player;
import com.tome.main.Repos.PlayerRepo;


@Service
public class TomeUserDetailsService implements UserDetailsService{
	
	@Autowired
	PlayerRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Player> player = this.repo.findByUsername(username);
		
		player.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " Not Found."));
		
		return player.map(TomeUserDetails::new).get();
	}

}
