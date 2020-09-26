package de.SassChris.HardJerky.services;

import de.SassChris.HardJerky.entities.Zutaten;
import de.SassChris.HardJerky.repositories.ZutatenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Author: Chris Saß, Created on: 24.09.2020
 */

@Service
@Transactional
public class ZutatenService {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private ZutatenRepository repository;

    public Optional<Zutaten> findById(Long id) {
        return repository.findById(id);
    }

    public List<Zutaten> listAll() {
        return repository.findAll();
    }

    public void save(Zutaten zutaten) {
        repository.save(zutaten);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Zutaten find(String zutatName) {
        return repository.findByZutatName(zutatName);
    }

}
