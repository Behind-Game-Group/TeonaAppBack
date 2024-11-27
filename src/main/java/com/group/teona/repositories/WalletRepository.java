package com.group.teona.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.teona.entities.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Integer> {
}
