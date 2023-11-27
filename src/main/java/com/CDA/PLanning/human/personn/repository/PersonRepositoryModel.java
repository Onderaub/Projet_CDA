package com.CDA.PLanning.human.personn.repository;
import com.CDA.PLanning.planning.absence.repository.PlanningAbsenceRepositoryModel;
import com.CDA.PLanning.planning.project.repository.PlanningProjectRepositoryModel;
import com.CDA.PLanning.planning.startEnd.repository.StartEndRepositoryModel;
import com.CDA.PLanning.planning.tool.repository.PlanningToolRepositoryModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @ManyToMany(mappedBy = "persons")
    private PlanningProjectRepositoryModel projects;
    @OneToMany(mappedBy = "person")
    private List<PlanningToolRepositoryModel> tools;
    @OneToMany(mappedBy = "person")
    private List<PlanningAbsenceRepositoryModel> absences;


    /**
     * Instantiates a new Person repository model.
     *
     * @param idPerson    the id person
     * @param name        the name
     * @param surname     the surname
     * @param adresse     the adresse
     * @param email       the email
     * @param phoneNumber the phone number
     */
    public PersonRepositoryModel(Long idPerson, String name, String surname, String adresse, String email, Long phoneNumber) {
        this.id = idPerson;
        this.name = name;
        this.surname = surname;
        this.adresse = adresse;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

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

    /**
     * Instantiates a new Person repository model.
     *
     * @param id the id
     */
    public PersonRepositoryModel(Long id) {
    }


    /**
     * Sets id person.
     *
     * @param personId the person id
     */
    public void setIdPerson(Long personId) {
        this.id= personId;
    }
}

