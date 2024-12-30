package com.group.teona.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class resetPasswordRequest {

	public String token;
	public String newPassword;
}
