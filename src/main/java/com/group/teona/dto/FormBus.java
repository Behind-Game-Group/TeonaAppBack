package com.group.teona.dto;



import java.util.List;

import com.group.teona.entities.City;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FormBus {
		
    private String numbers;
    private List<City> cities;
    
    
    

}
