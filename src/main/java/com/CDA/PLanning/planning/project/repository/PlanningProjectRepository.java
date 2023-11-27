package com.CDA.PLanning.planning.project.repository;

import org.springframework.data.repository.CrudRepository;

/**
 * The interface Planning project repository.
 */
public interface PlanningProjectRepository extends CrudRepository<PlanningProjectRepositoryModel, Long> {
    PlanningProjectRepositoryModel save(PlanningProjectRepositoryModel planningProjectRepositoryModel);
}
