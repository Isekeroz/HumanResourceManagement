package com.rest.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rest.entity.Country;

@Repository
public interface CountryDao extends JpaRepository<Country, Serializable>{

	@Query(value="SELECT * FROM country WHERE id =:countryId",nativeQuery=true)
	public List<Country> getCountryById(@Param("countryId") int countryId);

	@Query(value="SELECT * FROM country",nativeQuery=true)
	public List<Country> getCountries();
}
