package com.coding.tasks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.coding.tasks.Application;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TasksControllerITTest {

	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();
	private HttpHeaders headers = new HttpHeaders();
	private String userMsgRequest = "{\r\n" + "	\"name\" : \"test\",\r\n" + "	\"password\" : \"test123\"\r\n" + "}";
	private String taskMsgRequest = "{\r\n" + "	\"name\" : \"task1\",\r\n" + "	\"description\" : \"task desc\"\r\n"
			+ "}";
	private Long userId;
	private String users_path = "/users/";

	protected String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	@Before
	public void setup() {

	}

	@Test
	public void testACreateUserTask() {
		HttpEntity<String> entity = new HttpEntity<String>(userMsgRequest, headers);

		ResponseEntity<Long> response = restTemplate.postForEntity(createURLWithPort("/users/signup"), entity,
				Long.class);
		userId = response.getBody();

		entity = new HttpEntity<String>(taskMsgRequest, headers);
		response = restTemplate.postForEntity(createURLWithPort(users_path + userId + "/tasks"), entity, Long.class);

		Assert.assertTrue(response.getBody().longValue() > 0);

	}

	@Test
	public void testBGetUserTasks() {

		HttpEntity<String> entity = new HttpEntity<String>(userMsgRequest, headers);

		ResponseEntity<Long> response = restTemplate.postForEntity(createURLWithPort("/users/login"), entity,
				Long.class);

		userId = response.getBody().longValue();
		ResponseEntity<List> response1 = restTemplate.getForEntity(createURLWithPort(users_path + userId + "/tasks"),
				List.class);

		Assert.assertTrue(response1.getBody().size() > 0);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCDeleteUserTask() {

		HttpEntity<String> entity = new HttpEntity<String>(userMsgRequest, headers);

		ResponseEntity<Long> response = restTemplate.postForEntity(createURLWithPort("/users/login"), entity,
				Long.class);

		userId = response.getBody().longValue();

		@SuppressWarnings("rawtypes")
		ResponseEntity<List> response1 = restTemplate.getForEntity(createURLWithPort(users_path + userId + "/tasks"),
				List.class);

		Map<String, Integer> map = (HashMap<String, Integer>) response1.getBody().get(0);
		Integer taskId = new Integer(map.get("taskId"));

		restTemplate.delete(createURLWithPort(users_path + userId + "/tasks/" + taskId));

	}
}
