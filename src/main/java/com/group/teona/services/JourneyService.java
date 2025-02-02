package com.group.teona.services;


import org.springframework.stereotype.Service;

import com.group.teona.entities.Journey;
import com.group.teona.form.FormAddJourney;



@Service
public interface JourneyService {
	
	public Journey addJourney (FormAddJourney formJourney);
}
