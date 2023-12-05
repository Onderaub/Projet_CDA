package com.CDA.PLanning.human.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * The interface Person repository.
 */
public interface PersonRepository extends CrudRepository<PersonRepositoryModel, Long> {
    PersonRepositoryModel save(PersonRepositoryModel personnRepositoryModel);

    Iterable<PersonRepositoryModel> findAll();

    Optional<PersonRepositoryModel> findById(Long id);


    Optional<PersonRepositoryModel> findByName(String name);
}
