package de.SassChris.HardJerky.services;

import de.SassChris.HardJerky.entities.Controlling;
import de.SassChris.HardJerky.repositories.ControllingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Author: Chris Saß, Created on: 22.09.2020
 */

@Service
@Transactional
public class ControllingService {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private ControllingRepository repository;

    public void save(Controlling controlling) {
        repository.save(controlling);
    }

    public Controlling getByCharge(long charge) {
        return repository.findByCharge(charge);
    }
}