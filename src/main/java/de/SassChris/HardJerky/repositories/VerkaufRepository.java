package de.SassChris.HardJerky.repositories;

import de.SassChris.HardJerky.entities.Verkauf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Chris Saß, Created on: 18.09.2020
 */

@Repository
public interface VerkaufRepository extends JpaRepository<Verkauf, Long> {
    Verkauf findTopByOrderByIdDesc();
}
