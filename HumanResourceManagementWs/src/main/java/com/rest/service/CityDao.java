package com.rest.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rest.entity.City;

@Repository
public interface CityDao extends JpaRepository<City, Serializable>{

	@Query(value="SELECT * FROM city WHERE country_id =:countryId",nativeQuery=true)
	public List<City> getCityByCountryId(@Param("countryId") int countryId);

	@Query(value="SELECT * FROM city",nativeQuery=true)
	public List<City> getCities();
}
