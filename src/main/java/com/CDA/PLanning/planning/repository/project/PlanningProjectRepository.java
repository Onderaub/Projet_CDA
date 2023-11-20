package com.CDA.PLanning.planning.repository.project;

import org.springframework.data.repository.CrudRepository;

public interface PlanningProjectRepository extends CrudRepository<PlanningProjectRepositoryModel, Long> {
    PlanningProjectRepositoryModel save(PlanningProjectRepositoryModel planningProjectRepositoryModel);
}
