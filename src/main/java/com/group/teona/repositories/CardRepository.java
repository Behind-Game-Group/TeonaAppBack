
package com.group.teona.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.teona.entities.Pass;

@Repository
public interface CardRepository extends JpaRepository<Pass,Long> {
}
