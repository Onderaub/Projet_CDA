package com.CDA.PLanning.planning.project.repository;

import org.springframework.data.repository.CrudRepository;

public interface PlanningProjectRepository extends CrudRepository<PlanningProjectRepositoryModel, Long> {
    PlanningProjectRepositoryModel save(PlanningProjectRepositoryModel planningProjectRepositoryModel);
}
