package de.SassChris.HardJerky.services;

import de.SassChris.HardJerky.entities.Verkauf;
import de.SassChris.HardJerky.repositories.VerkaufRepository;
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
public class VerkaufService {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private VerkaufRepository repository;

    public List<Verkauf> listAll() {
        return repository.findAll();
    }

    public void save(Verkauf verkauf) {
        repository.save(verkauf);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public Optional<Verkauf> getById(long id) {
        return repository.findById(id);
    }
}
