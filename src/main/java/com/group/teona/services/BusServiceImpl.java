package com.group.teona.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.dto.FormBus;
import com.group.teona.entities.Bus;
import com.group.teona.entities.City;
import com.group.teona.repositories.BusRepository;
import com.group.teona.repositories.CityRepository;

@Service
public class BusServiceImpl implements BusService {
	
	@Autowired
    BusRepository busRepository;
	
	@Autowired
    CityRepository cityRepository;
	
	public Bus addBus (FormBus formBus) {
		
		Bus bus = new Bus();
		bus.setNumbers(formBus.getNumbers());
		/*
		List<String> cities = formBus.getCities();
		for (String cityName : cities) {
			City city = cityRepository.findByName(cityName);
			bus.getCities().add(city);	
			
		}

		List<City> cities = formBus.getCities();
		for (City city : cities) {
			City newCity = new City();
			newCity.setName(city.getName());
			newCity.setCoordinates(city.getCoordinates());
			cityRepository.save(newCity);
			bus.getCities().add(newCity);
		}
		*/
		 return busRepository.save(bus);

		}

}
