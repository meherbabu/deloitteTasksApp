package com.coding.tasks.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
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

import com.coding.tasks.service.TaskService;
import com.coding.tasks.vo.TaskVO;

@RunWith(SpringRunner.class)
public class TasksControllerTest {

	@TestConfiguration
	static class TasksControllerTestContextConfiguration {

		@Bean
		public TasksController taskController() {
			return new TasksController();
		}
	}

	@Autowired
	private TasksController tasksController;

	@MockBean
	private TaskService taskService;

	@Test
	public void testGetUserTasks() {
		Mockito.when(taskService.getUserTasks(1L)).thenReturn(new ArrayList<TaskVO>());
		List<TaskVO> response = tasksController.getUserTasks(1L);
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testCreateUserTask() {
		Mockito.when(taskService.createTask(Mockito.anyString(), Mockito.anyString())).thenReturn(1L);
		Long taskId = tasksController.createUserTask(Mockito.anyString(), Mockito.anyString());
		Assert.assertTrue(taskId == 1);
	}

	@Test
	public void testDeleteTask() {
		taskService.deleteTask(Mockito.anyLong());
		ResponseEntity<?> response = tasksController.deleteTask(Mockito.anyLong());
		Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
	}

}
