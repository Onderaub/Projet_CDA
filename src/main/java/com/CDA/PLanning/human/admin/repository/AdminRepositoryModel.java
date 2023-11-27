package com.CDA.PLanning.human.admin.repository;
import com.CDA.PLanning.human.personn.repository.PersonRepositoryModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * The type Admin repository model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="admin")
public class AdminRepositoryModel extends PersonRepositoryModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idadmin")
    private Long idAdmin;
    private Long idperson;

}

