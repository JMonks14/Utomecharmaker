package com.tome.main.Controllers;

import java.security.Principal;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tome.main.Enitities.Player;
import com.tome.main.Exceptions.PlayerAlreadyExistsException;
import com.tome.main.Services.PlayerServices;
import com.tome.main.util.GenericResponse;

@RestController
@RequestMapping("/player")
@CrossOrigin
public class PlayerController {
	
	@Autowired
	PlayerServices service;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
    private MessageSource messages;
	
	@Autowired
	private Environment env;
	
	@PostMapping("/reg")
	public ResponseEntity<String> register(@RequestBody Player player) {
		try {
			this.service.create(player);
			return new ResponseEntity<>("Account Successfully Created", HttpStatus.CREATED);
		} catch (PlayerAlreadyExistsException ex){
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/current")
	@ResponseBody
	public String getCurrentUser(Principal principal) {
		return principal.getName();
	}
	
	@GetMapping("/view/{id}")
	public Player view(@PathVariable int id) { 
		return this.service.viewById(id);
	}
	
	@GetMapping("/viewlatest")
	public Player viewLatest() {
		return this.service.viewLatest();
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<Player> update(@RequestBody Player newPlayer, @PathVariable int id) {
		Player updated = this.service.update(newPlayer, id);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}
	@GetMapping("/find/{username}")
	public Player findByUsername(@PathVariable String username) {
		return this.service.findByUsername(username);
	}
	
	private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
	
	// Reset password
    @PostMapping("/resetPassword")
    public GenericResponse resetPassword(final HttpServletRequest request, @RequestParam("email") final String userEmail) {
        final Player player = service.findUserByEmail(userEmail);
        if (player != null) {
            final String token = UUID.randomUUID().toString();
            service.createPasswordResetTokenForUser(player, token);
            mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, player));
        }
        return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
    }
    
    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final Player player) {
        final String url = contextPath + "/user/changePassword?token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, player);
    }
    
    private SimpleMailMessage constructEmail(String subject, String body, Player player) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(player.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

}
