package com.group.teona.repositories;

import com.group.teona.entities.City;
import com.group.teona.entities.Station;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station,Long> {
}
