package com.group.teona.services;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.entities.Bus;
import com.group.teona.entities.City;
import com.group.teona.entities.Journey;
import com.group.teona.entities.Station;
import com.group.teona.entities.User;
import com.group.teona.form.FormAddJourney;
import com.group.teona.form.FormAddStation;
import com.group.teona.form.FormAddReservation;
import com.group.teona.repositories.BusRepository;
import com.group.teona.repositories.CityRepository;
import com.group.teona.repositories.JourneyRepository;
import com.group.teona.repositories.StationRepository;
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
	
	@Autowired
    StationRepository stationRepository;
	
	
	public Journey addJourney (FormAddJourney formJourney) {
		Journey journey = new Journey();
		Bus bus = busRepository.findByNumbers(formJourney.getBus());
		journey.setBus(bus);
		
		
		for (FormAddStation station : formJourney.getStations()) {
			City city = cityRepository.findByName(station.getCity());
			
			Station stationSave = new Station();
			stationSave.setCity(city);
			stationSave.setSchedules(station.getSchedules());
			stationRepository.save(stationSave);	
			
			journey.getStations().add(stationSave);
		}		
		
		journey.getStations().sort(Comparator.comparing(Station::getSchedules));
		journey.setDateDepart(journey.getStations().getFirst().getSchedules());
		journey.setDateArrival(journey.getStations().getLast().getSchedules());
		journey.setDuration(Duration.between(journey.getDateDepart(), journey.getDateArrival()));
		journey.setPrice(25 * journey.getStations().size() );
				
		 journeyRepository.save(journey);
		
		 for (Station station : journey.getStations()) {
			 station.setJourney(journey);
			 stationRepository.save(station);
		 }
		 
		 return journey;
	}
	

}
