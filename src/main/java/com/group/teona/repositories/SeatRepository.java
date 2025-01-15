package com.group.teona.repositories;

import com.group.teona.entities.Journey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Journey,Long> {

}
