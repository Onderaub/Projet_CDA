package com.CDA.PLanning.human.personn.repository;
import com.CDA.PLanning.planning.absence.repository.PlanningAbsenceRepositoryModel;
import com.CDA.PLanning.planning.project.repository.PlanningProjectRepositoryModel;
import com.CDA.PLanning.planning.startEnd.repository.StartEndRepositoryModel;
import com.CDA.PLanning.planning.tool.repository.PlanningToolRepositoryModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="person")
public class PersonRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerson")
    private Long id;
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

    @ManyToMany(mappedBy = "person")
    private PlanningProjectRepositoryModel startEnd;
    @OneToMany(mappedBy = "person")
    private List<PlanningToolRepositoryModel> tools;
    @OneToMany(mappedBy = "person")
    private List<PlanningAbsenceRepositoryModel> absence;


    public PersonRepositoryModel(Long id, String name, String surname, String adresse, String email, Long phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.adresse = adresse;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public PersonRepositoryModel(String name, String surname, String adresse, String email, Long phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.adresse = adresse;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public PersonRepositoryModel(Long id) {
    }
}
