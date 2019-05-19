package com.ayushya.spring.service;

import com.ayushya.spring.helper.GlobalConstants;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class EmailService {

	public boolean sendEmail() {
		Email from = new Email("shreyas.shivajirao@gmail.com");
		String subject = "Test email from shreyas";
		Email to = new Email("shreyas.shivajirao@gmail.com");
		Content content = new Content("text/plain", "Trying to send it for the first time");
		Mail mail = new Mail(from, subject, to, content);
		
		SendGrid sg = new SendGrid(GlobalConstants.SENDGRID_TOKEN);
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
}
