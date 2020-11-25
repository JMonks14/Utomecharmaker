package com.tome.main.Controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.tome.main.Enitities.Player;
import com.tome.main.Exceptions.PlayerNotFoundException;
import com.tome.main.Services.PlayerServices;
import com.tome.main.util.Utility;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {
	
	@Autowired
	private PlayerServices playerService;
	
	@Autowired
	private JavaMailSender mailSender;

	@GetMapping("/forgot_password")
	public String showForgotPasswordForm(Model model) {
		return "forgot_password_form";
	}
	
	@PostMapping("/forgot_password")
	public String processForgotPasswordForm(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String token = RandomString.make(45);
		try {
			playerService.updateResetPasswordToken(token, email);
			
			String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
			
			sendEmail(email, resetPasswordLink);
			
			model.addAttribute("message", "Success! You should see the reset email in your inbox shortly");
		} catch (PlayerNotFoundException e) {
			model.addAttribute("error", "No account using the email address you entered was found");
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			model.addAttribute("error", "Error when sending email");
		}		
		return "forgot_password_form";
	}
	
	private void sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("unfinished.tome@gmail.com", "Unfinished Tome");
		helper.setTo(email);
		
		String subject = "Here's the link to reset your password";
		
		String content = "<p> Hello,</p>"
				+ "<p>You have requested to reset your password.</p>"
				+ "<p>You may change your password by following the link below:</p>"
				+ "<p><a href =\"" + resetPasswordLink +  "\">Change my Password</a></p>";
		
		helper.setSubject(subject);
		helper.setText(content, true);
		
		mailSender.send(message);
	}
	
	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
		try {
			Player player = playerService.getByResetToken(token);
			model.addAttribute("token", token);
			return "reset_password_form";
		} catch (PlayerNotFoundException e) {
			return "InvalidToken";
		}
		
		
	}
	
	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("confirmPassword");
		
		try {
			Player player = playerService.getByResetToken(token);
			playerService.updatePassword(player, password);
			model.addAttribute("message", "Password Updated Successfully");
			return "reset_successful";
		} catch (PlayerNotFoundException e) {
			return "InvalidToken";
		}
		
	}
	
	@GetMapping("/test")
	public String showTestPage() {
		return "testpage";
	}
}
