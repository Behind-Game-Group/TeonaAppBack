package com.group.teona.repositories;
import com.group.teona.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.teona.entities.Card;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
	
	 List<Card> findAllByWalletAndIsActive(Wallet wallet, boolean isActive);
   boolean existsByWalletAndIsActive(Wallet wallet, boolean isActive);	
   Card findFirstByWalletAndIsActive(Wallet wallet, boolean isActive);
}
