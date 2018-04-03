package com.rest.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rest.entity.District;

@Repository
public interface DistrictDao extends JpaRepository<District, Serializable>{

	@Query(value="SELECT * FROM district WHERE city_id =:cityId",nativeQuery=true)
	public List<District> getDistrictByCityId(@Param("cityId") int cityId);

	@Query(value="SELECT * FROM district",nativeQuery=true)
	public List<District> getDistricts();

	@Query(value="SELECT * FROM district WHERE id =:id",nativeQuery=true)
	public District getDistrictById(@Param("id") int id);
}
