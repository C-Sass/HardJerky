package de.SassChris.HardJerky.repositories;

import de.SassChris.HardJerky.entities.Zutaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Chris Saß, Created on: 24.09.2020
 */

@Repository
public interface ZutatenRepository extends JpaRepository<Zutaten, Long> {

    Zutaten findByZutatName(String zutatName);
}
