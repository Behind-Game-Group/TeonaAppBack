package com.group.teona.repositories;

import com.group.teona.entities.Adress;
import com.group.teona.entities.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Adress,Long> {
	List<Adress> findByUser(User user);
}
