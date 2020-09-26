package de.SassChris.HardJerky.services;

import de.SassChris.HardJerky.entities.Marinade;
import de.SassChris.HardJerky.repositories.MarinadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Author: Chris Saß, Created on: 25.09.2020
 */

@Service
@Transactional
public class MarinadeService {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private MarinadeRepository repository;

    public Marinade find(String name) {
        return repository.findByName(name);
    }

    public List<Marinade> marinadeList() {
        return repository.findAll();
    }

    public void save(Marinade marinade) {
        repository.save(marinade);
    }

    public void truncate() {
        repository.deleteAll();
    }
}
