package com.CDA.PLanning.human.repository;

import org.springframework.data.repository.CrudRepository;

/**
 * The interface Person repository.
 */
public interface PersonRepository extends CrudRepository<PersonRepositoryModel, Long> {
    PersonRepositoryModel save(PersonRepositoryModel personnRepositoryModel);

    Iterable<PersonRepositoryModel> findAll();
}
