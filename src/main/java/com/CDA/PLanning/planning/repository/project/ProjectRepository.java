package com.CDA.PLanning.planning.repository.project;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * The interface Person repository.
 */
public interface ProjectRepository extends CrudRepository<ProjectRepositoryModel, Long> {
    ProjectRepositoryModel save(ProjectRepositoryModel personnRepositoryModel);

    Iterable<ProjectRepositoryModel> findAll();

    Optional<ProjectRepositoryModel> findById(Long id);


    Optional<ProjectRepositoryModel> findByName(String name);
}
