package com.nisum.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nisum.model.Message;
import com.nisum.model.User;
import com.nisum.service.UserAuthenticationService;
import com.nisum.util.NKartUtil;

@RestController("/")
public class UserAuthenticationController {

	private String uname = "username";

	@Autowired
	UserAuthenticationService userService;

	public String home() {
		return "index";
	}

	@PostMapping("/authenticateUser")
	@ResponseBody
	public Message authenticateUser(@RequestBody User user, HttpSession session) {

		Map<String,Boolean> resultMap = userService.authenticateUser(user);

		if (resultMap != null && resultMap.get("validUser") && resultMap.get("confirmUser") ) {
			session.setAttribute(uname, user.getUsername());
			return new Message("success");
		} else if(resultMap != null && resultMap.get("validUser")) {
			return new Message("User Not Activated");
		}else {
			return new Message("Username & Password Mismatch");
		}
	}

	@PostMapping("/registerUser")
	@ResponseBody
	public Message registerUser(@RequestBody User user, HttpSession session) {
		session.setAttribute(uname, user.getUsername());
		String secureToken = NKartUtil.generatePIN();
		user.setSecureToken(secureToken);
		return userService.createUser(user);

	}

	@RequestMapping("/confirmUser")
	public ModelAndView confirmUser(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "secureToken") String secureToken) {

		boolean isCreated = false;

		isCreated = userService.confirmUser(username, password, secureToken);
		if (isCreated)
			return new ModelAndView("index", "message", "success");
		else
			return new ModelAndView("index", "message", "Failure");
	}

	@PostMapping("/getuser")
	@ResponseBody
	public Message getUserName(HttpSession session) {
		return new Message((String) session.getAttribute(uname));
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("index");
	}

}
