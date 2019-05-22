package com.ayushya.spring.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayushya.spring.service.SMSService;

@RestController
@RequestMapping("/sms")
public class SMSController {

	@PostMapping(value = "/")
	public String createAndSendSMS(@RequestBody JSONObject SMSDetails) {
		if(new SMSService().sendSMS(SMSDetails)) {
			return "SUCCESS";
		}
		return null;
	}
}
