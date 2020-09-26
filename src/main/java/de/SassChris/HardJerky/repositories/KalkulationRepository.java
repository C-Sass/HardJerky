package de.SassChris.HardJerky.repositories;

import de.SassChris.HardJerky.entities.Kalkulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Chris Saß, Created on: 26.09.2020
 */

@Repository
public interface KalkulationRepository extends JpaRepository<Kalkulation, Long> {

    List<Kalkulation> findAllByOrderByPreisAsc();

}
