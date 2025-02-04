package com.group.teona.services;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.entities.Bus;
import com.group.teona.entities.City;
import com.group.teona.entities.Journey;
import com.group.teona.entities.Reservation;
import com.group.teona.entities.Station;
import com.group.teona.entities.User;
import com.group.teona.form.FormAddJourney;
import com.group.teona.form.FormAddReservation;
import com.group.teona.repositories.BusRepository;
import com.group.teona.repositories.CityRepository;
import com.group.teona.repositories.JourneyRepository;
import com.group.teona.repositories.ReservationRepository;
import com.group.teona.repositories.UserRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
    ReservationRepository reservationRepository;
	
	@Autowired
    JourneyRepository journeyRepository;
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
    BusRepository busRepository;
	
	@Autowired
    CityRepository cityRepository;
	
	
	
	public Reservation addReservation (User user, Journey journey, FormAddReservation formAddReservation ) {
		
		Reservation reservation = new Reservation();
		
		reservation.setDate(formAddReservation.getDate());
		reservation.setJourney(journey);
		reservation.setUser(user);
		
		City cityD = cityRepository.findByName(formAddReservation.getCityDeparture());
		City cityA = cityRepository.findByName(formAddReservation.getCityArrival());

		for (Station station : journey.getStations()) {
			if(station.getCity().equals(cityD) ||
			   station.getCity().equals(cityA) 
			   ) {
				reservation.getStations().add(station);				
			}
		}
		
		reservation.getStations().sort(Comparator.comparing(Station::getSchedules));
		
		
		for (Station station : journey.getStations()) {
			if(station.getSchedules().isAfter(reservation.getStations().getFirst().getSchedules()) &&
			   station.getSchedules().isBefore(reservation.getStations().getLast().getSchedules())
			   ) {
				reservation.getStations().add(station);				
			}
		}
				
		reservation.setAdult(formAddReservation.isAdult());
		reservation.setChildren(formAddReservation.isChildren());
		reservation.setWithBike(formAddReservation.isWithBike());
		
		reservation.setFinalPrice( (journey.getPrice() / journey.getStations().size() ) * reservation.getStations().size() );
		
		if(reservation.isChildren()) {
			reservation.setFinalPrice((reservation.getFinalPrice() * 0.5));
		}
		if(reservation.isWithBike()) {
			reservation.setFinalPrice((reservation.getFinalPrice() * 1.5));

		}
		
		
		return reservationRepository.save(reservation);
	}

}
