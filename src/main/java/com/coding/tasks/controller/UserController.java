package com.coding.tasks.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.tasks.service.UserService;
import com.coding.tasks.vo.UserVO;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<Long> createUser(@RequestBody String message) {
		Long userId = userService.verifyLoginCredentials(message);
		if (userId != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(userId);
		}
		userId = userService.createUser(message);
		return ResponseEntity.status(HttpStatus.CREATED).body(userId);
	}

	@PostMapping("/login")
	public ResponseEntity<Long> loginUser(@RequestBody String message) {
		Long userId = userService.verifyLoginCredentials(message);
		if (userId == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(userId);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(userId);
		}
	}

	@GetMapping
	public ResponseEntity<List<UserVO>> getUsers() {
		List<UserVO> usersList = new ArrayList<UserVO>();
		usersList = userService.getUsers();
		return ResponseEntity.ok(usersList);
	}

}
