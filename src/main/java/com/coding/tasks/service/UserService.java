package com.coding.tasks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coding.tasks.vo.UserVO;

@Service
public interface UserService {

	Long createUser(String userRequest);

	List<UserVO> getUsers();

	Long verifyLoginCredentials(String msg);
	
	Long verifySignup(String msg);
}
