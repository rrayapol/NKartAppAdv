package com.nisum.service;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.nisum.dao.MongoDao;
import com.nisum.model.Message;
import com.nisum.model.User;

@Service
public class UserAuthenticationService {

	@Autowired
	private MongoDao mongoDAO;

	private String uname = "username";

	@Autowired
	private JavaMailSender sender;
	
	@Value("${server.port}")
	private String appPort;

	public Map<String,Boolean> authenticateUser(User user) {
		Map<String,Boolean> reslutMap = mongoDAO.authenticate(user.getUsername(), user.getPassword());
		return reslutMap;
	}

	public Message createUser(User user) {

		boolean isCreated = mongoDAO.createUser(user);
		if (isCreated) {
			try {
				sendEmail(user);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return new Message("success");
		} else
			return new Message("Username Already Exists");

	}

	private void sendEmail(User user) throws Exception {

		StringBuffer messageBody = new StringBuffer();
		
		messageBody.append(
				"<html><body>Hello " + user.getUsername() + ", <p>You are Successfully Regitsered with NKart..");
		messageBody.append("Please click <a href='http://localhost:"+appPort+"/confirmUser?username=");
		messageBody.append(user.getUsername() + "&password=" + user.getPassword()+ "&secureToken="
				+ user.getSecureToken() + "'");
		messageBody.append(">here</a> for confirmation.</p><body></html>");

		MimeMessage message = sender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setTo(user.getEmail());

		helper.setText(messageBody.toString(), true);

		helper.setSubject("Welcome to NKart!!!!!");

		sender.send(message);

	}

	public boolean confirmUser(String uname, String pwd, String secureToken) {
		try {
			mongoDAO.confirmUser(uname,pwd, secureToken);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
