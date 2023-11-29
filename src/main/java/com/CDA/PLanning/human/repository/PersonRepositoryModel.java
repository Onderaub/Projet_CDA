package com.CDA.PLanning.human.repository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

/**
 * The type Person repository model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="person")
public class PersonRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personId")
    private Long personId;
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

    /*
    @ManyToMany
    @JoinTable(
            name = "person_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<ProjectRepositoryModel> projects;
   // @OneToMany
   // @JoinColumn(name="idtool")
   // private List<PlanningToolRepositoryModel> tools;
   // @OneToMany
   // @JoinColumn(name="idperson")
   // private List<PlanningAbsenceRepositoryModel> absences;


    /**
     * Instantiates a new Person repository model.
     *   the id person
     * @param name        the name
     * @param surname     the surname
     * @param adresse     the adresse
     * @param email       the email
     * @param phoneNumber the phone number
     */


    /**
     * Instantiates a new Person repository model.
     *
     * @param name        the name
     * @param surname     the surname
     * @param adresse     the adresse
     * @param email       the email
     * @param phoneNumber the phone number
     */
    public PersonRepositoryModel(String name, String surname, String adresse, String email, Long phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.adresse = adresse;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public PersonRepositoryModel(String name) {

    }
}

