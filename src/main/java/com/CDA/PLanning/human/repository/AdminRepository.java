package com.CDA.PLanning.human.repository;


import org.springframework.data.repository.CrudRepository;

/**
 * The interface Admin repository.
 */
public interface AdminRepository extends CrudRepository<AdminRepositoryModel, Long> {
    AdminRepositoryModel save(AdminRepositoryModel adminRepositoryModel);

    ;
}
