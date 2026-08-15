package com.esunbank_homework.social_media_system.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esunbank_homework.social_media_system.Model.AuthModel;
import com.esunbank_homework.social_media_system.Service.AuthService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class AuthController {	

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
	
    @PostMapping("Login")
	public String login(@RequestBody AuthModel request, HttpSession session) {
		return authService.login(request, session);
	}
	
    @PostMapping("Logout")
	public String logout(HttpSession session) {
		return authService.logout(session);
	}
	
	@PostMapping("Register")
	public String register(@RequestBody AuthModel request) {
		return authService.register(request);
	}

	@PutMapping("UpdataUser")
	public String updataUser(@RequestBody AuthModel request, HttpSession session) {
		return authService.updataUser(request, session);
	}
		
}
