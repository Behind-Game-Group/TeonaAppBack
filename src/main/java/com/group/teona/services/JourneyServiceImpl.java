package com.group.teona.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.dto.GetJourney;
import com.group.teona.dto.GetStation;
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
	
	@Override
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
		journey.setPrice(formJourney.getPrice());
				
		 journeyRepository.save(journey);
		
		 for (Station station : journey.getStations()) {
			 station.setJourney(journey);
			 stationRepository.save(station);
		 }
		 
		 return journey;
	}
	
	@Override
	public List<GetJourney> getJourney (String CityDeparture, String CityArrival, LocalDate dateDepart) {
		
		List <String> cities = new ArrayList<>();
		cities.add(CityDeparture);
		cities.add(CityArrival);
		System.out.println("cityArrival : " + CityArrival);
		
		
		LocalDateTime startOfDay = dateDepart.atStartOfDay();
		LocalDateTime startOfNextDay = dateDepart.plusDays(1).atStartOfDay();
		/*
		System.out.println(startOfDay);	
		System.out.println(startOfNextDay);	
		*/
		
		List <Journey> journeys = journeyRepository.findJourneysByAttributes(cities, startOfDay, startOfNextDay);
		
		List <GetJourney> getJourneys = new ArrayList<>();
		
		for (Journey journey : journeys) {
			GetJourney getJourney = new GetJourney();
			getJourney.setId(journey.getId());
			getJourney.setBusNumber(journey.getBus().getNumbers());
			
			// Ajoute les stations du trajet qui contiennent les villes entrées
			for (Station station : journey.getStations()) {
				
				if(station.getCity().getName().equals(CityDeparture) || station.getCity().getName().equals(CityArrival)) {
					System.out.println("ajouté : " + station.getCity().getName());
					getJourney.getStations().add(station);
				}
			}
			
			// Trie les stations récupérées par horaires
			getJourney.getStations().sort(Comparator.comparing(Station::getSchedules));
			
			// Ajoute les stations de trajet entre les stations des villes entrées
			for (Station station : journey.getStations()) {
				if(station.getSchedules().isAfter(getJourney.getStations().getFirst().getSchedules()) &&
					station.getSchedules().isBefore(getJourney.getStations().getLast().getSchedules())) {
					
					getJourney.getStations().add(station);

				}
			}
			getJourney.setDateDepart(getJourney.getStations().getFirst().getSchedules());
			getJourney.setDateArrival(getJourney.getStations().getLast().getSchedules());
			getJourney.setDuration(Duration.between(getJourney.getDateDepart(), getJourney.getDateArrival()));
			getJourney.setPrice( (journey.getPrice() / journey.getStations().size() ) * getJourney.getStations().size() );
			
			for (Station station : getJourney.getStations()) {
				 GetStation getStation = new GetStation();
				 getStation.setCity(station.getCity().getName());
				 getStation.setSchedules(station.getSchedules());
				 getJourney.getGetStations().add(getStation);
							
						}
			
			getJourney.setStations(null);
			getJourney.getGetStations().sort(Comparator.comparing(GetStation::getSchedules));
			getJourneys.add(getJourney);
			
			
		}
		
		return getJourneys;
		
	}
	

}
