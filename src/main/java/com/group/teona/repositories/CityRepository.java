package com.group.teona.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.group.teona.entities.City;


@Repository
public interface CityRepository extends JpaRepository<City,Long> {
}
