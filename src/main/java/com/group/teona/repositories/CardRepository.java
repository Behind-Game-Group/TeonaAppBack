package com.group.teona.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.teona.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
