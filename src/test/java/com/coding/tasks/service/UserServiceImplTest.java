package com.coding.tasks.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.coding.tasks.model.User;
import com.coding.tasks.repository.UserRepository;
import com.coding.tasks.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

	@TestConfiguration
	static class UserServiceImplTestContextConfiguration {

		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}
	}

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	private String userMsgRequest = "{\r\n" + "	\"name\" : \"test\",\r\n" + "	\"password\" : \"test123\"\r\n" + "}";

	@Before
	public void setup() {

	}

	@Test
	public void testGetUsers() {
		List<User> usersList = new ArrayList<User>();
		Mockito.when(userRepository.findAll()).thenReturn(usersList);
		Assert.assertTrue(userService.getUsers().size() == 0);
	}

	@Test(expected = RuntimeException.class)
	public void testGetUsersThrowsException() {
		Mockito.when(userRepository.findAll()).thenThrow(new RuntimeException());
		Assert.assertTrue(userService.getUsers().size() == 0);
	}

	@Test
	public void testCreateUser() {
		User user = new User();
		user.setUserId(1L);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		Assert.assertEquals(1, userService.createUser(userMsgRequest).longValue());
	}

	@Test
	public void testVerifyLoginCredentials() {
		User user = new User();
		user.setUserId(1L);
		Mockito.when(userRepository.findUser(Mockito.anyString(), Mockito.anyString())).thenReturn(1L);
		Assert.assertEquals(1, userService.verifyLoginCredentials(userMsgRequest).longValue());
	}

	@Test
	public void testSignup() {
		User user = new User();
		user.setUserId(1L);
		Mockito.when(userRepository.findUser(Mockito.anyString())).thenReturn(1L);
		Assert.assertEquals(1, userService.verifySignup(userMsgRequest).longValue());
	}

}
