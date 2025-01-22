package com.group.teona.services;


import org.springframework.stereotype.Service;

import com.group.teona.dto.FormBus;
import com.group.teona.entities.Bus;



@Service
public interface BusService {
	
	Bus addBus (FormBus formBus);
}
