package com.group.teona.services;


import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.group.teona.dto.GetJourney;
import com.group.teona.entities.Journey;
import com.group.teona.form.FormAddJourney;



@Service
public interface JourneyService {
	
	public Journey addJourney (FormAddJourney formJourney);
	
	public List<GetJourney> getJourney (String CityDeparture, String CityArrival, LocalDate dateDepart);
}
