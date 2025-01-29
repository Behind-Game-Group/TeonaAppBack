package com.group.teona.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDto {
	private String cardTitle;
    private boolean isActive;
    private Long adressId; 
    private Long walletId;
    private String topUp;
    private Double cardPrice;
    private String paymentStatus;
}
