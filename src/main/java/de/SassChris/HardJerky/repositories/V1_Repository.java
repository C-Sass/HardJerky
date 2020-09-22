package de.SassChris.HardJerky.repositories;

import de.SassChris.HardJerky.entities.Verarbeitung_1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface V1_Repository extends JpaRepository<Verarbeitung_1, Long> {
}
