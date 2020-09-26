package de.SassChris.HardJerky.services;

import de.SassChris.HardJerky.entities.Kalkulation;
import de.SassChris.HardJerky.repositories.KalkulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Author: Chris Saß, Created on: 26.09.2020
 */

@Service
@Transactional
public class KalkulationService {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private KalkulationRepository repository;

    public void save(Kalkulation kalkulation) {
        repository.save(kalkulation);
    }

    public List<Kalkulation> kalkulationList() {
        return repository.findAllByOrderByPreisAsc();
    }

    public void truncate() {
        repository.deleteAll();
    }
}
