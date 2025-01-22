package com.group.teona.services;


import org.springframework.stereotype.Service;

import com.group.teona.entities.City;



@Service
public interface CityService {
	
	public City addCity (City city);
}
