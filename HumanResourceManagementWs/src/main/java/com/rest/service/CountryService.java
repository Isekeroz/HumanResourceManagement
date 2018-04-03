package com.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.entity.Country;

@Service
public class CountryService {
	@Autowired
	private CountryDao countryDao;

	public List<Country> getCountries(){
		return countryDao.getCountries();
	}
	
	public List<Country> getCountryById(int countryId) {
		return countryDao.getCountryById(countryId);
	}
}
