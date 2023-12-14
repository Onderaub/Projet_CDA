package com.CDA.PLanning.planning.repository.absence;


import com.CDA.PLanning.human.repository.PersonRepositoryModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AbsenceRepository extends CrudRepository<AbsenceRepositoryModel, Long> {
    Optional<AbsenceRepositoryModel> findByName(String name);
    AbsenceRepositoryModel save(AbsenceRepositoryModel absenceRepositoryModel);

    Iterable<AbsenceRepositoryModel> findAll();

    Optional<AbsenceRepositoryModel> findById(Long id);

}
