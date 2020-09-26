package de.SassChris.HardJerky.repositories;

import de.SassChris.HardJerky.entities.Marinade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Chris Saß, Created on: 25.09.2020
 */

@Repository
public interface MarinadeRepository extends JpaRepository<Marinade, Long> {

    Marinade findByName(String name);
}
