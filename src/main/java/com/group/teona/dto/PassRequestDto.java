package com.group.teona.dto;

import com.group.teona.entities.Adress;
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
public class PassRequestDto  {
    private String cardTitle;
    private Double cardPrice;
    private boolean isActive;
    private Long adressId; 
    private Long walletId;
    private String image;
}
