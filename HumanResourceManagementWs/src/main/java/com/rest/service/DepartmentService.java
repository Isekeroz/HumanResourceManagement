package com.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.entity.Department;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;

	public List<Department> getDepartments(){
		return departmentDao.getDepartments();
	}
	
	public Department getDepartmentById(int departmentId) {
		return departmentDao.getDepartmentById(departmentId);
	}

	public void save(Department department) {
		departmentDao.save(department);
	}

	public int controlManager(int departmentId) {
		return departmentDao.controlManager(departmentId);
	}

}
