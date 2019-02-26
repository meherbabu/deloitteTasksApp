package com.coding.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coding.tasks.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u.userId from User u where u.name = :name and u.password = :password")
	public Long findUser(@Param("name") final String name, @Param("password") final String password);

	@Query("select u.userId from User u where u.name = :name")
	public Long findUser(@Param("name") final String name);

	@Query("select u.userId from User u where u.name = :name")
	public Long findByName(@Param("name") final String name);

}
