package com.group.teona.repositories;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group.teona.entities.Journey;

@Repository
public interface JourneyRepository extends JpaRepository<Journey,Long> {
	
	@Query("SELECT j FROM Journey j JOIN j.stations s JOIN s.city c WHERE c.name IN :cityNames AND j.dateDepart >= :startDate AND j.dateDepart < :endDate")
	List<Journey> findJourneysByAttributes(@Param("cityNames") List<String> cityNames,
										   @Param("startDate") LocalDateTime startDate,
										   @Param("endDate") LocalDateTime endDate);
	
	
	
	



}
