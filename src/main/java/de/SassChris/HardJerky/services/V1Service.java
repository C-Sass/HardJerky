package de.SassChris.HardJerky.services;

import de.SassChris.HardJerky.entities.Verarbeitung_1;
import de.SassChris.HardJerky.repositories.V1_Repository;
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
public class V1Service {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private V1_Repository repository;

    public List<Verarbeitung_1> listAll() {
        return repository.findAll();
    }

    public void save(Verarbeitung_1 v1) {
        repository.save(v1);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public Optional<Verarbeitung_1> getById(long id) {
        return repository.findById(id);
    }

    public List<Verarbeitung_1> getByCharge(Long charge) {
        return repository.findAllByCharge(charge);
    }
}
