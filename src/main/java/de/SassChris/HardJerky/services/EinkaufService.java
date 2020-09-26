package de.SassChris.HardJerky.services;

import de.SassChris.HardJerky.entities.Einkauf;
import de.SassChris.HardJerky.repositories.EinkaufRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Author: Chris Saß, Created on: 18.09.2020
 */

@Service
@Transactional
public class EinkaufService {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private EinkaufRepository repository;

    public List<Einkauf> listAll() {
        return repository.findAll();
    }

    public void save (Einkauf einkauf) {
        repository.save(einkauf);
    }

    public void deleteById (long id) {
        repository.deleteById(id);
    }

    public Optional<Einkauf> getById(long id) {
        return repository.findById(id);
    }

    public Einkauf last() {
        return repository.findTopByOrderByIdDesc();
    }

    public long currentCharge() {
        return last().getCharge();
    }

    public Einkauf getByCharge(Long charge) {
        return repository.findByCharge(charge);
    }
}
