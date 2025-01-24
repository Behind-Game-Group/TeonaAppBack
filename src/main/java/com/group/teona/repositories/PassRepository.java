package com.group.teona.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.teona.entities.Pass;
import com.group.teona.entities.Wallet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PassRepository extends JpaRepository<Pass,Long> {
	List<Pass> findAllByExpirationDateBeforeAndIsActiveTrue(LocalDate date);
	 List<Pass> findAllByWalletAndIsActive(Wallet wallet, boolean isActive);
    boolean existsByWalletAndIsActive(Wallet wallet, boolean isActive);	
    Pass findFirstByWalletAndIsActive(Wallet wallet, boolean isActive);
}
