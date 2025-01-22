package com.group.teona.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.teona.entities.Journey;

@Repository
public interface JourneyRepository extends JpaRepository<Journey,Long> {

}
