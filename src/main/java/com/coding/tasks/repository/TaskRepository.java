package com.coding.tasks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coding.tasks.model.Task;
import com.coding.tasks.model.User;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByUser(User user);
}