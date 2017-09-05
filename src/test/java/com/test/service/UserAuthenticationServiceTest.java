package com.test.service;

import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;

import com.nisum.dao.MongoDao;
import com.nisum.model.Message;
import com.nisum.model.User;
import com.nisum.service.UserAuthenticationService;

@RunWith(MockitoJUnitRunner.class)
public class UserAuthenticationServiceTest {

	@InjectMocks
	UserAuthenticationService userService = new UserAuthenticationService();
	
	@Mock
	private MongoDao mongoDAO;
	
	@Mock
	private JavaMailSender sender;
	
	@Mock
	MimeMessage mimeMessage;

	
	@Test
   public void testAuthenticateUser() {
		User user = new User();
		user.setUsername("nisum");
		user.setPassword("123");
		when(mongoDAO.authenticate("nisum","123")).thenReturn(new HashMap<String,Boolean>() );
		Map<String,Boolean> result = userService.authenticateUser(user);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testCreateUserSuccess() {
		
		User user = new User();
		user.setUsername("nisum");
		user.setPassword("n123");
		user.setEmail("rrayapol@nisum.com");
		when(mongoDAO.createUser(user)).thenReturn(true);
		when(sender.createMimeMessage()).thenReturn(mimeMessage);
		Message result = userService.createUser(user);
		Assert.assertEquals(result.getMessage(), "success");
		
	}
	
	@Test
    public void testCreateUserFailure() {
    	User user = new User();
		when(mongoDAO.createUser(user)).thenReturn(false);
		Message result = userService.createUser(user);
		Assert.assertEquals(result.getMessage(), "Username Already Exists");
	}
	
	@Test
	public void testConfirmUser() {
		
		when(mongoDAO.confirmUser("nisum","123","1234")).thenReturn(true);

		boolean result = userService.confirmUser("nisum","123", "1234");
		Assert.assertEquals(result, true);

	}

	
}
