package com.tome.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tome.main.Enitities.Player;
import com.tome.main.Exceptions.PlayerAlreadyExistsException;
import com.tome.main.Exceptions.PlayerNotFoundException;
import com.tome.main.Repos.PlayerRepo;


@Service
public class PlayerServices {

	@Autowired
	PlayerRepo repo;
	
	@Autowired
	PasswordEncoder encoder;
	
	public PlayerServices(PlayerRepo repo) {
		super();
		this.repo=repo;
	}
	
	private boolean emailExists(final String email) {
        return repo.findByEmail(email).isPresent();
    }
	
	private boolean usernameExists(final String username) {
        return repo.findByUsername(username).isPresent();
    }
	
	public Player create(Player player) {
		
		if (emailExists(player.getEmail())) {
			throw new PlayerAlreadyExistsException("Registration Failed: The email address you entered is already in use");
		}
		if (usernameExists(player.getUsername())) {
			throw new PlayerAlreadyExistsException("Registration Failed: The username you entered is already in use");
		}
		
		player.setPassword(encoder.encode(player.getPassword()));
		player.setRole("ROLE_USER");
		player.setActiveChar(0);
		return this.repo.save(player);
	}
	
	public Player viewById(int id) {
		Player found = this.repo.findById(id).orElseThrow(PlayerNotFoundException::new);
		return found;
	}
	public Player viewLatest() {
		Player latest = this.repo.findLatest();
		return latest;
	}
	
	public boolean passwordCorrect(String password, int pid) {
		Player player = viewById(pid);
		if (encoder.matches(password, player.getPassword())) return true;
		else return false;
	}
	
	public Player update(Player newPlayer, int id) {
		try {
			Player player = viewById(id);
		if (newPlayer.getFirst_name()!=null)
		player.setFirst_name(newPlayer.getFirst_name());
		if (newPlayer.getLast_name()!=null)
		player.setLast_name(newPlayer.getLast_name());
		if (newPlayer.getUsername()!=null)
		player.setUsername(newPlayer.getUsername());
		if (newPlayer.getActiveChar()!=0)
		player.setActiveChar(newPlayer.getActiveChar());
		Player saved = this.repo.save(player);
		return saved;
		}
		catch (PlayerNotFoundException e) {
			return null;
		}
		
	}
	public Player findByUsername(String username) {
		return this.repo.findByUsername(username).orElseThrow(PlayerNotFoundException::new);
	}
	
	public void updateResetPasswordToken(String token, String email) {
		Player player = repo.findByEmail(email).orElseThrow(PlayerNotFoundException::new);
		player.setResetPasswordToken(token);
		repo.save(player);
	}
	
	public Player getByResetToken(String token) {
		return repo.findByResetPasswordToken(token).orElseThrow(PlayerNotFoundException::new);
	}
	
	public void updatePassword(Player player, String newPassword) {
		String encodedPw = encoder.encode(newPassword);
		
		player.setPassword(encodedPw);
		player.setResetPasswordToken(null);
		repo.save(player);
	}

}
