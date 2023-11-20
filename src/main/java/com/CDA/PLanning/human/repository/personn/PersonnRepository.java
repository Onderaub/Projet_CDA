package com.CDA.PLanning.human.repository.personn;

import org.springframework.data.repository.CrudRepository;

public interface PersonnRepository extends CrudRepository<PersonnRepositoryModel, Long> {
    PersonnRepositoryModel save(PersonnRepositoryModel personnRepositoryModel);

    Iterable<PersonnRepositoryModel> findAll();
}
