package com.coding.tasks.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.coding.tasks.service.UserService;
import com.coding.tasks.vo.UserVO;

@RunWith(SpringRunner.class)
public class UserControllerTest {

	@TestConfiguration
	static class UserControllerTestContextConfiguration {

		@Bean
		public UserController userController() {
			return new UserController();
		}
	}

	@Autowired
	private UserController userController;

	@MockBean
	private UserService userService;

	@Before
	public void setup() {

	}

	@Test
	public void testCreateUser_UserAlreadyExists() {
		Mockito.when(userService.verifyLoginCredentials(Mockito.anyString())).thenReturn(1L);
		ResponseEntity<Long> response = userController.createUser(Mockito.anyString());
		Assert.assertTrue(response.getStatusCode() == HttpStatus.CONFLICT);
	}
	
	@Test
	public void testCreateUser_NewUser() {
		Mockito.when(userService.verifyLoginCredentials(Mockito.anyString())).thenReturn(null);
		Mockito.when(userService.createUser(Mockito.anyString())).thenReturn(1L);
		ResponseEntity<Long> response = userController.createUser(Mockito.anyString());
		Assert.assertTrue(response.getStatusCode() == HttpStatus.CREATED);
		Assert.assertTrue(response.getBody().longValue() == 1 );
	}
	
	@Test
	public void testLoginUser_NewUser() {
		Mockito.when(userService.verifyLoginCredentials(Mockito.anyString())).thenReturn(null);
		ResponseEntity<Long> response = userController.loginUser(Mockito.anyString());
		Assert.assertTrue(response.getStatusCode() == HttpStatus.FORBIDDEN);
	}
	
	@Test
	public void testLoginUser_ExistingUser() {
		Mockito.when(userService.verifyLoginCredentials(Mockito.anyString())).thenReturn(1L);
		ResponseEntity<Long> response = userController.loginUser(Mockito.anyString());
		Assert.assertTrue(response.getBody().longValue() == 1);
		Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void testGetUsers() {
		List<UserVO> usersList = new ArrayList<UserVO>();
		Mockito.when(userService.getUsers()).thenReturn(usersList);
		ResponseEntity<List<UserVO>> response = userController.getUsers();
		Assert.assertTrue(response.getBody().size() == 0);
		Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
	}

}
