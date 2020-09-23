package de.SassChris.HardJerky.services;

import de.SassChris.HardJerky.entities.Verarbeitung_2;
import de.SassChris.HardJerky.repositories.V2_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Author: Chris Saß, Created on: 19.09.2020
 */

@Service
@Transactional
public class V2Service {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private V2_Repository repository;

    public List<Verarbeitung_2> listAll() {
        return repository.findAll();
    }

    public void save(Verarbeitung_2 v2) {
        repository.save(v2);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public Optional<Verarbeitung_2> getById(long id) {
        return repository.findById(id);
    }

    public Verarbeitung_2 last() {
        return repository.findTopByOrderByIdDesc();
    }
}
