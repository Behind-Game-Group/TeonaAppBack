package com.group.teona.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.group.teona.dto.VisaCardRequest;
import com.group.teona.entities.VisaCard;

public interface VisaCardRepository extends JpaRepository<VisaCard,Long> {

	List<VisaCard> findByWalletId(Long walletId);
}
