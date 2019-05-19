package com.ayushya.spring.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayushya.spring.service.EmailService;

@RestController
@RequestMapping("/email")
public class emailController {

	@PostMapping(value = "/")
	public String createAndSendEmail() {
		if(new EmailService().sendEmail()) {
			return "SUCCESS";
		}
		return "FAILED";
	}
	
}
