package com.group.teona.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.teona.entities.User;
import com.group.teona.entities.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {
	Optional<Wallet> findByUser(User user);
}
