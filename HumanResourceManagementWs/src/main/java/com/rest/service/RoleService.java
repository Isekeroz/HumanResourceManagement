package com.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.entity.Role;

@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao;

	public List<Role> getRoles(){
		return roleDao.getRoles();
	}
	
	public Role getRoleById(int roleId) {
		return roleDao.getRoleById(roleId);
	}

	public void save(Role role) {
		roleDao.save(role);
	}

}
