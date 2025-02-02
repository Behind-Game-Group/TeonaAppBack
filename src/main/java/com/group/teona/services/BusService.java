package com.group.teona.services;


import org.springframework.stereotype.Service;

import com.group.teona.entities.Bus;
import com.group.teona.form.FormBus;



@Service
public interface BusService {
	
	Bus addBus (FormBus formBus);
}
