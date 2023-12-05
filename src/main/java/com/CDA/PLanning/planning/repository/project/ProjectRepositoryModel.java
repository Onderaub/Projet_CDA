package com.CDA.PLanning.planning.repository.project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Person repository model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="project")
public class ProjectRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "directeur")
    private String directeur;
    @Column(name = "place")
    private String place;


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

     */
    public ProjectRepositoryModel(String name, String directeur, String place) {
        this.name = name;
        this.directeur = directeur;
        this.place = place;

    }


    public ProjectRepositoryModel(String name) {

    }
}

