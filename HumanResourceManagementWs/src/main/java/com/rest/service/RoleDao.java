package com.rest.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rest.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Serializable>{

	@Query(value="SELECT * FROM role WHERE id =:roleId",nativeQuery=true)
	public Role getRoleById(@Param("roleId") int roleId);

	@Query(value="SELECT * FROM role",nativeQuery=true)
	public List<Role> getRoles();
}
