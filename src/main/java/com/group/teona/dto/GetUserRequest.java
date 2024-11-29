package com.group.teona.dto;

import java.util.List;

import com.group.teona.enums.EnumRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUserRequest {
	
    private Long id;
    
	@Enumerated(EnumType.STRING)
	private List< EnumRole> role;


}
