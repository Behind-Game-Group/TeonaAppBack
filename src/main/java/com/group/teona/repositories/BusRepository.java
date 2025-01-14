package com.group.teona.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.teona.entities.Bus;



@Repository
public interface BusRepository extends JpaRepository<Bus,Long> {
}
