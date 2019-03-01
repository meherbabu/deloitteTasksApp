package com.coding.tasks.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.tasks.model.User;
import com.coding.tasks.repository.UserRepository;
import com.coding.tasks.service.UserService;
import com.coding.tasks.vo.UserVO;
import com.google.gson.Gson;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public Long createUser(String userRequest) {
		return userRepo.save(getUserFromJson(userRequest)).getUserId();
	}

	@Override
	public List<UserVO> getUsers() {
		List<UserVO> userVOList = new ArrayList<UserVO>();
		userRepo.findAll().forEach(user -> userVOList.add(convertModelToVO(user)));

		return userVOList;
	}

	@Override
	public Long verifyLoginCredentials(String msg) {
		User user = getUserFromJson(msg);
		Long userId = userRepo.findUser(user.getName(), user.getPassword());
		return userId;
	}

	@Override
	public Long verifySignup(String msg) {
		User user = getUserFromJson(msg);
		Long userId = userRepo.findUser(user.getName());
		return userId;
	}

	private User getUserFromJson(String userRequest) {
		Gson gson = new Gson();
		UserVO userVO = gson.fromJson(userRequest, UserVO.class);
		User user = convertVOToModel(userVO);

		return user;
	}

	private User convertVOToModel(UserVO userVO) {

		User user = new User();
		user.setName(userVO.getName());
		user.setPassword(userVO.getPassword());

		return user;
	}

	private UserVO convertModelToVO(User user) {

		UserVO userVO = new UserVO();
		userVO.setName(user.getName());
		userVO.setPassword(user.getPassword());
		userVO.setUserId(user.getUserId());
		return userVO;
	}

}
