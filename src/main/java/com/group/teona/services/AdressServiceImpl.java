package com.group.teona.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.teona.dto.FormTeonaPass;
import com.group.teona.entities.Adress;
import com.group.teona.entities.User;
import com.group.teona.repositories.AdressRepository;
import com.group.teona.repositories.UserRepository;
;

@Service
public class AdressServiceImpl implements AdressService {
	

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveFormWithUser(FormTeonaPass formRequest, User user) {
        Adress adress = mapToAdress(formRequest);
        adress.setUser(user);
        adressRepository.save(adress);
    }

    public void saveFormWithoutUser(FormTeonaPass formRequest) {
        Adress adress = mapToAdress(formRequest);
        adressRepository.save(adress);
    }

    private Adress mapToAdress(FormTeonaPass formRequest) {
        Adress adress = new Adress();
        adress.setStreetName(formRequest.getStreetName());
        adress.setStreetNameOptional(formRequest.getStreetNameOptional());
        adress.setPostCode(formRequest.getPostCode());
        adress.setCity(formRequest.getCity());
        adress.setCountry(formRequest.getCountry());
        adress.setPhoneNumber(formRequest.getPhoneNumber());
        adress.setFirstName(formRequest.getFirstName());
        adress.setLastName(formRequest.getLastName());
        adress.setImage(formRequest.getImage());
        return adress;
    }

	

}
