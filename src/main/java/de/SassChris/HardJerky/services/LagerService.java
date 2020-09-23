package de.SassChris.HardJerky.services;

import de.SassChris.HardJerky.entities.Lager;
import de.SassChris.HardJerky.repositories.LagerRepository;
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
public class LagerService {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private LagerRepository repository;

    public List<Lager> listAll() {
        return repository.findAll();
    }

    public void save(Lager lager) {
        repository.save(lager);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public Optional<Lager> getById(long id) {
        return repository.findById(id);
    }

    public Lager getByMarinade(String marinade) {
        return repository.findByMarinade(marinade);
    }

    public Lager last() {
        return repository.findTopByOrderByIdDesc();
    }
}
