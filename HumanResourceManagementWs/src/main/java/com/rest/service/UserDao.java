package com.rest.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rest.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Serializable> {

	@Query(value="SELECT * FROM user WHERE id =:userId",nativeQuery=true)
	public List<User> getUserById(@Param("userId") int userId);

	@Query(value="SELECT * FROM user",nativeQuery=true)
	public List<User> getUsers();

	@Transactional
	@Modifying
	@Query(value="UPDATE user SET department_id = NULL WHERE id =:userId",nativeQuery=true)
	public void removeFromDepartment(@Param("userId") int userId);
	
}
