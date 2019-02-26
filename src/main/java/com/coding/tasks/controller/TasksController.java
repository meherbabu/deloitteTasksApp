package com.coding.tasks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.tasks.service.TaskService;
import com.coding.tasks.vo.TaskVO;

@RestController
@RequestMapping(value = "/users")
public class TasksController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/{userId}/tasks")
	public List<TaskVO> getUserTasks(@PathVariable("userId") Long userId) {
		return taskService.getUserTasks(userId);
	}

	@PostMapping("/{userId}/tasks")
	public Long createUserTask(@PathVariable("userId") String userId, @RequestBody String taskMsg) {
		return taskService.createTask(userId, taskMsg);
	}

	@DeleteMapping("/tasks/{taskId}")
	@CrossOrigin
	public ResponseEntity<?> deleteTask(@PathVariable("taskId") Long taskId) {
		taskService.deleteTask(taskId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
