package com.CDA.PLanning.human.repository;


import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * The interface Admin repository.
 */
public interface AdminRepository extends CrudRepository<AdminRepositoryModel, Long> {
    AdminRepositoryModel save(AdminRepositoryModel adminRepositoryModel);



    Iterable<AdminRepositoryModel> findAll();

    Optional<AdminRepositoryModel> findById(Long id);


    Optional<AdminRepositoryModel> findByName(String name);
}
