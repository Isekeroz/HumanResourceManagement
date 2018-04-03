package com.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.entity.District;

@Service
public class DistrictService {
	@Autowired
	private DistrictDao districtDao;

	public List<District> getDistricts(){
		return districtDao.getDistricts();
	}
	
	public List<District> getDistrictByCityId(int cityId) {
		return districtDao.getDistrictByCityId(cityId);
	}
	
	public District getDistrictById(int id) {
		return districtDao.getDistrictById(id);
	}
}
