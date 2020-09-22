package de.SassChris.HardJerky.repositories;

import de.SassChris.HardJerky.entities.Lager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LagerRepository extends JpaRepository<Lager, Long> {
}
