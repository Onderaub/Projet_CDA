package com.CDA.PLanning.planning.repository.tool;



import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ToolRepository extends CrudRepository<ToolRepositoryModel, Long> {
    ToolRepositoryModel save(ToolRepositoryModel toolRepositoryModel);

    Iterable<ToolRepositoryModel> findAll();

    Optional<ToolRepositoryModel> findById(Long id);


    Optional<ToolRepositoryModel> findByName(String name);
}
