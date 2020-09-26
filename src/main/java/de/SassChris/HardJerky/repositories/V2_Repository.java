package de.SassChris.HardJerky.repositories;

import de.SassChris.HardJerky.entities.Verarbeitung_2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface V2_Repository extends JpaRepository<Verarbeitung_2, Long> {

    Verarbeitung_2 findTopByOrderByIdDesc();

    List<Verarbeitung_2> findAllByCharge(Long charge);
}
