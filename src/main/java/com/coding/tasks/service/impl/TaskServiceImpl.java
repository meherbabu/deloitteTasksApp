package com.coding.tasks.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.tasks.model.Task;
import com.coding.tasks.model.User;
import com.coding.tasks.repository.TaskRepository;
import com.coding.tasks.repository.UserRepository;
import com.coding.tasks.service.TaskService;
import com.coding.tasks.vo.TaskVO;
import com.google.gson.Gson;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository tasksRepo;

	@Autowired
	private UserRepository userRepo;

	public Long createTask(String userId, String taskRequest) {
		Gson gson = new Gson();
		TaskVO taskVO = gson.fromJson(taskRequest, TaskVO.class);
		taskVO.setUserId(Long.valueOf(userId));
		taskVO.setUpdatedDt(new Date());
		Task task = convertVOToModel(taskVO);

		return tasksRepo.save(task).getTaskId();
	}

	@Override
	public List<TaskVO> getUserTasks(String userName) {
		Long userId = userRepo.findByName(userName);
		User user = new User(userId);
		List<Task> tasksList = tasksRepo.findByUser(user);
		List<TaskVO> tasksVOList = new ArrayList<TaskVO>();
		tasksList.forEach(task -> tasksVOList.add(convertModelToVO(task)));
		return tasksVOList;
	}

	@Override
	public List<TaskVO> getUserTasks(Long userId) {
		User user = new User(userId);
		List<Task> tasksList = tasksRepo.findByUser(user);
		List<TaskVO> tasksVOList = new ArrayList<TaskVO>();
		tasksList.forEach(task -> tasksVOList.add(convertModelToVO(task)));
		return tasksVOList;
	}

	@Override
	public void deleteTask(Long taskId) {
		tasksRepo.deleteById(taskId);
	}

	private TaskVO convertModelToVO(Task task) {

		TaskVO taskVO = new TaskVO();

		taskVO.setDescription(task.getDescription());
		taskVO.setName(task.getName());
		taskVO.setTaskId(task.getTaskId());
		taskVO.setUserId(task.getUser().getUserId());
		LocalDate ld = convertToLocalDateViaInstant(task.getLastUpdatedDt());

		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		taskVO.setUpdatedDateStr(ld.format(df));

		return taskVO;
	}

	private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	private Task convertVOToModel(TaskVO taskVO) {

		Task task = new Task();

		task.setDescription(taskVO.getDescription());
		task.setName(taskVO.getName());
		task.setLastUpdatedDt(taskVO.getUpdatedDt());

		User user = new User(taskVO.getUserId());

		task.setUser(user);

		return task;
	}

}
