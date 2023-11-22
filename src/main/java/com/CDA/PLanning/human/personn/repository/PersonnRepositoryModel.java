package com.CDA.PLanning.human.personn.repository;
import com.CDA.PLanning.planning.absence.repository.PlanningAbsenceRepositoryModel;
import com.CDA.PLanning.planning.tool.repository.PlanningToolRepositoryModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="personn")
public class PersonnRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersonn")
    private Long id;

    @OneToMany(mappedBy = "personn")
    private List<PlanningToolRepositoryModel> tools;

    @OneToMany(mappedBy = "personn")
    private List<PlanningAbsenceRepositoryModel> absence;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "e-mail")
    private String email;

    @Column(name = "phoneNumber")
    private Long phoneNumber;


    public PersonnRepositoryModel(Long id, String name, String surname, String adresse, String email, Long phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.adresse = adresse;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public PersonnRepositoryModel(String name, String surname, String adresse, String email, Long phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.adresse = adresse;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
