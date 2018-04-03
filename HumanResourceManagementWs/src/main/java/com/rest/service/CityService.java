package com.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.entity.City;

@Service
public class CityService {
	@Autowired
	private CityDao cityDao;

	public List<City> getCities(){
		return cityDao.getCities();
	}
	
	public List<City> getCityByCountryId(int countryId) {
		return cityDao.getCityByCountryId(countryId);
	}
}
