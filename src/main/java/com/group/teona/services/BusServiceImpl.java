package com.group.teona.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.entities.Bus;
import com.group.teona.entities.City;
import com.group.teona.form.FormBus;
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
			
		return busRepository.save(bus);
	
		}

}
