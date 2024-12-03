package com.group.teona.entities;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group.teona.enums.EnumRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class User  implements UserDetails{
	  @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    
	    @Column(name = "lastName", length = 25, nullable = false)
		private String lastName;
	    
	    @Column(name = "firstName", length = 25, nullable = false)
	   	private String firstName;
	    
	    @Column(name = "gender", nullable = false)	
	    private String gender;
	    
	    @Column(name = "country", nullable = false)
	    private String country;
	    
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
		private Set<Adress> adresses = new HashSet<>();
		
		@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
		private Wallet wallet;
		
		@Lob
		@Column(name = "role", nullable = false)
		@Enumerated(EnumType.STRING)
		private List< EnumRole> role;
		
	
		@Column(name = "language", nullable = true)     
		private String language;
		
		
		@Column(name = "verification_code", nullable = true)
		private String verificationCode;
		
		@Column(name = "code_expiration_time", nullable = true)
	    private LocalDateTime codeExpirationTime;
		
		@Column(name = "verified", nullable = true)
	    private boolean verified = false;
		
		@Column(name = "teonaPassenger", nullable = true)
	    private boolean teonaPassenger = false;
		
		@Column(name = "teonaGroup", nullable = true)
	    private boolean teonaGroup = false;
		
		@Column(name = "reset_token")
		private String resetToken;

		@Column(name = "token_expiration_time")
		private LocalDateTime tokenExpirationTime;
		
	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

	        for (EnumRole roleEnum : role) {
	            grantedAuthorities.add(new SimpleGrantedAuthority(roleEnum.toString()));
	        }

	        return grantedAuthorities;
	    }

		  
		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return email;
		}
		
		

}