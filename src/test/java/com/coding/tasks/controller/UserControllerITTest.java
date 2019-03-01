package com.coding.tasks.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.coding.tasks.Application;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerITTest {

	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();
	private HttpHeaders headers = new HttpHeaders();
	private String userMsgRequest = "{\r\n" + "	\"name\" : \"test\",\r\n" + "	\"password\" : \"test123\"\r\n" + "}";

	@Before
	public void setup() {
	}

	@Test
	public void testACreateUser_NewUser() {

		HttpEntity<String> entity = new HttpEntity<String>(userMsgRequest, headers);

		ResponseEntity<Long> response = restTemplate.postForEntity(createURLWithPort("/users/signup"), entity,
				Long.class);

		Assert.assertTrue(response.getBody().longValue() > 0);
		// Assert.assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}

	@Test
	public void testBCreateUser_ExistingUser() {

		HttpEntity<String> entity = new HttpEntity<String>(userMsgRequest, headers);

		ResponseEntity<Long> response = restTemplate.postForEntity(createURLWithPort("/users/signup"), entity,
				Long.class);

		Assert.assertTrue(response.getBody().longValue() > 0);
		Assert.assertTrue(response.getStatusCode() == HttpStatus.CONFLICT);
	}

	@Test
	public void testCLoginUser_ExistingUser() {

		HttpEntity<String> entity = new HttpEntity<String>(userMsgRequest, headers);

		ResponseEntity<Long> response = restTemplate.postForEntity(createURLWithPort("/users/login"), entity,
				Long.class);

		Assert.assertTrue(response.getBody().longValue() > 0);
		Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
	}

	@Test
	public void testDLoginUser_NewUser() {

		userMsgRequest = "{\r\n" + "	\"name\" : \"test1\",\r\n" + "	\"password\" : \"test123\"\r\n" + "}";
		HttpEntity<String> entity = new HttpEntity<String>(userMsgRequest, headers);

		ResponseEntity<Long> response = restTemplate.postForEntity(createURLWithPort("/users/login"), entity,
				Long.class);

		Assert.assertTrue(response.getStatusCode() == HttpStatus.FORBIDDEN);
	}

	@Test
	public void testEGetUsers() {
		ResponseEntity<List> response = restTemplate.getForEntity(createURLWithPort("/users"), List.class);
		Assert.assertTrue(response.getBody().size() == 1);
		Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);

	}

	protected String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
