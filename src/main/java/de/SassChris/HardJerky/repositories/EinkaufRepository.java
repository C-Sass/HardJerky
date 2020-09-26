package de.SassChris.HardJerky.repositories;

import de.SassChris.HardJerky.entities.Einkauf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EinkaufRepository extends JpaRepository<Einkauf, Long> {

    Einkauf findTopByOrderByIdDesc();

    Einkauf findByCharge(Long charge);
}
