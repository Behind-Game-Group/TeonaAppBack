package com.group.teona.entities;


import java.util.Date;
import java.util.Set;

import com.group.teona.enums.EnumGender;
import com.group.teona.enums.EnumLanguage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "numero", length = 4, nullable = false)
	private String numero;
	
	@Column(name = "road", length = 75, nullable = false)
	private String road;
	
	@Column(name = "postal_code", length = 5, nullable = false)
	private String postalCode;

	@Column(name = "city", length = 25, nullable = false)
	private String city;
	
	@Column(name = "country", length = 25, nullable = false)
	private String country;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

}
