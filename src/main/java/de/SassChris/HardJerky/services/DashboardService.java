package de.SassChris.HardJerky.services;

import de.SassChris.HardJerky.repositories.EinkaufRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Author: Chris Saß, Created on: 22.09.2020
 */

@Service
@Transactional
public class DashboardService {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private EinkaufRepository repository;
}
