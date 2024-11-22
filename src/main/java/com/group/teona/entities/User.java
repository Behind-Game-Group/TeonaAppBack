package com.group.teona.entities;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.group.teona.enums.EnumGender;
import com.group.teona.enums.EnumLanguage;
import com.group.teona.enums.EnumRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", length = 25, nullable = false)
	private String name;
    
    @Column(name = "firstname", length = 25, nullable = false)
   	private String firstname;
    
    @Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
    private EnumGender gender;
    
    @Column(name = "residenceCountry", nullable = false)
    private String residenceCountry;
    
	@Column(name = "date_of_birth")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(name = "email", length = 32, nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", length = 500, nullable = false)
	private String password;
	
	@Column(name = "phoneNumber", length = 12, nullable = true, unique = true)
	private String phoneNumber;
	

	@Column(name = "passPicture", length = 500, nullable = true)
	private String passPicture;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	Set<Adress> adresses;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Wallet wallet;
	
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumRole role;
	
	
	@Lob
	@Column(name = "language") 
	@Enumerated(EnumType.STRING)	    
	private List<EnumLanguage> languages;
	
	
	@Column(name = "verification_code", nullable = true)
	private String verificationCode;
	
	@Column(name = "code_expiration_time", nullable = true)
    private LocalDateTime codeExpirationTime;
	
	@Column(name = "is_verified", nullable = true)
    private boolean isVerified = false;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	
	



}