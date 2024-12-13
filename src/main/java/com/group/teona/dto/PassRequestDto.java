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
public class PassRequestDto {
    private String cardTitle;
    private Double cardPrice;
    private boolean isActive;
    private Long adressId; 
 
}
