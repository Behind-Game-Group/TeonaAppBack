package com.group.teona.services;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.dto.FormAddJourney;
import com.group.teona.dto.FormAddUserJourney;
import com.group.teona.entities.Bus;
import com.group.teona.entities.City;
import com.group.teona.entities.Journey;
import com.group.teona.entities.User;
import com.group.teona.repositories.BusRepository;
import com.group.teona.repositories.CityRepository;
import com.group.teona.repositories.JourneyRepository;
import com.group.teona.repositories.UserRepository;

@Service
public class JourneyServiceImpl implements JourneyService {
	
	@Autowired
    JourneyRepository journeyRepository;
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
    BusRepository busRepository;
	
	@Autowired
    CityRepository cityRepository;
	
	
	public Journey addJourney (FormAddJourney formJourney) {
		Journey journey = new Journey();
		journey.setDateDepart(formJourney.getDateDepart());
		journey.setDateArrival(formJourney.getDateArrival());
		journey.setDuration(Duration.between(journey.getDateDepart(), journey.getDateArrival()));
		Bus bus = busRepository.findByNumbers(formJourney.getBus());
		journey.setBus(bus);
		List<String> cities = formJourney.getCities();
		for (String city : cities) {
			City newCity = cityRepository.findByName(city);
				journey.getCities().add(newCity);
			
		}
		 return journeyRepository.save(journey);
	}
	
	
	
	public void addJourneyUser (Long userId, Long journeyId, FormAddUserJourney userJourneyDto) {
		
		Optional<User> user = userRepository.findById(userId);
		Optional<Journey> journey = journeyRepository.findById(journeyId);
		
		if(user.isPresent() && journey.isPresent()) {
			User userFound = user.get();
			Journey journeyFound = journey.get();

			journeyFound.setSeat(userJourneyDto.getSeat());
			journeyFound.setAdult(userJourneyDto.isAdult());
			journeyFound.setChildren(userJourneyDto.isChildren());
			journeyFound.setWithBike(userJourneyDto.isWithBike());
			journeyFound.getUser().add(userFound);
			
			journeyRepository.save(journeyFound);
		}

	}

}
