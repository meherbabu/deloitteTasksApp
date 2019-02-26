package com.coding.tasks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coding.tasks.vo.TaskVO;

@Service
public interface TaskService {

	Long createTask(String userName, String message);

	List<TaskVO> getUserTasks(Long userId);

	List<TaskVO> getUserTasks(String userName);
	
	void deleteTask(Long taskId);

}
