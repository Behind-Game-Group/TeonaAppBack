package com.group.teona.services;


import org.springframework.stereotype.Service;

import com.group.teona.dto.FormAddJourney;
import com.group.teona.dto.FormAddUserJourney;
import com.group.teona.entities.Journey;



@Service
public interface JourneyService {
	
	public Journey addJourney (FormAddJourney formJourney);
	public void addJourneyUser (Long userId, Long journeyId, FormAddUserJourney userJourneyDto);
}
