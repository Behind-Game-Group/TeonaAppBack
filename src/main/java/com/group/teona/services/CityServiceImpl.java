package com.group.teona.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.entities.City;
import com.group.teona.repositories.CityRepository;

@Service
public class CityServiceImpl implements CityService  {
	
	@Autowired
	CityRepository cityRepository;
	
	public City addCity (City city) {
		 return cityRepository.save(city);
	}


}
