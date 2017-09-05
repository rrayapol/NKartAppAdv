package com.test.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.nisum.controller.UserAuthenticationController;
import com.nisum.model.Message;
import com.nisum.model.User;
import com.nisum.service.UserAuthenticationService;

@RunWith(MockitoJUnitRunner.class)
public class UserAuthenticationControllerTest {

	@InjectMocks
	UserAuthenticationController userController = new UserAuthenticationController();

	@Mock
	UserAuthenticationService userService;

	@Test
	public void testAuthenticateUserSuccess() {

		User user = new User();
		user.setConfirmed(true);
		
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		map.put("validUser",true);
		map.put("confirmUser", true);

		HttpSession httpSession = mock(HttpSession.class);

		when(userService.authenticateUser(user)).thenReturn(map);

		Message result = userController.authenticateUser(user, httpSession);

		Assert.assertEquals(result.getMessage(), "success");

	}

	@Test
	public void testAuthenticateUserFailure() {

		User user = new User();
		user.setConfirmed(false);

		HttpSession httpSession = mock(HttpSession.class);

		when(userService.authenticateUser(user)).thenReturn(null);

		Message result = userController.authenticateUser(user, httpSession);

		Assert.assertEquals(result.getMessage(), "Username & Password Mismatch");

	}

	@Test
	public void testRegisterUser() {

		User user = new User();
		HttpSession httpSession = mock(HttpSession.class);

		when(userService.createUser(user)).thenReturn(new Message("success"));

		Message result = userController.registerUser(user, httpSession);

		Assert.assertEquals(result.getMessage(), "success");

	}

	@Test
	public void testConfirmUserSuccess() {

		when(userService.confirmUser("test", "123", "1234")).thenReturn(true);
		HttpSession httpSession = mock(HttpSession.class);

		ModelAndView result = userController.confirmUser("test", "123", "1234");

		Assert.assertEquals(result.getViewName(), "index");

	}

	@Test
	public void testConfirmUserFailure() {

		when(userService.confirmUser("test", "123", "1234")).thenReturn(false);
		
		HttpSession httpSession = mock(HttpSession.class);

		ModelAndView result = userController.confirmUser("test", "123", "1234");

		Assert.assertNotNull(result);

	}

	@Test
	public void testGetUserName() {

		HttpSession httpSession = mock(HttpSession.class);
		httpSession.setAttribute("username", "nisum123");

		Message result = userController.getUserName(httpSession);

		Assert.assertNull(result.getMessage());

	}

	@Test
	public void testLogout() {

		HttpSession httpSession = mock(HttpSession.class);
		ModelAndView result = userController.logout(httpSession);

		Assert.assertEquals(result.getViewName(), "index");

	}

}
