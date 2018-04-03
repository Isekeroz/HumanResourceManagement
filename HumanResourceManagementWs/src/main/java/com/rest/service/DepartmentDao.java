package com.rest.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rest.entity.Department;

@Repository
public interface DepartmentDao extends JpaRepository<Department, Serializable>{

	@Query(value="SELECT * FROM department WHERE id =:departmentId",nativeQuery=true)
	public Department getDepartmentById(@Param("departmentId") int departmentId);

	@Query(value="SELECT * FROM department",nativeQuery=true)
	public List<Department> getDepartments();

	@Query(value="SELECT COUNT(*) FROM user WHERE department_id =:departmentId AND role_id=3",nativeQuery=true)
	public int controlManager(@Param("departmentId") int departmentId);
}
