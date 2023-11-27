package com.CDA.PLanning.planning.project.repository;
import com.CDA.PLanning.human.admin.repository.AdminRepositoryModel;
import com.CDA.PLanning.human.personn.repository.PersonRepositoryModel;
import com.CDA.PLanning.human.service.admin.AdminServiceModel ;
import com.CDA.PLanning.planning.startEnd.repository.StartEndRepositoryModel;
import com.CDA.PLanning.planning.startEnd.service.StartEndServiceModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Client repository model.
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name="planningProject")

public class PlanningProjectRepositoryModel {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column (name="idProject")
            private Long id;
            @Column(name="name")
            private String name;
            @Column(name="place")
            private String place;
            @Column (name="color")
            private String color;

            @ManyToMany(mappedBy = "project")
            @PrimaryKeyJoinColumn
            private List<PersonRepositoryModel> person;
            @OneToMany(mappedBy = "project")
            private StartEndRepositoryModel startEnd;
            @OneToOne
            @PrimaryKeyJoinColumn
            private AdminRepositoryModel admin;


    /**
     * Instantiates a new Planning project repository model.
     *
     * @param name     the name
     * @param place    the place
     * @param color    the color
     * @param admin    the admin
     * @param startEnd the start end
     */
    public PlanningProjectRepositoryModel(String name, String place, String color, AdminRepositoryModel admin, StartEndRepositoryModel startEnd) {
        this.name=name;
        this.place=place;
        this.color=color;
        this.startEnd=startEnd;
        this.admin=admin;
        this.person = new ArrayList<>();
    }

    /**
     * Instantiates a new Planning project repository model.
     *
     * @param name     the name
     * @param place    the place
     * @param color    the color
     * @param admin    the admin
     * @param startEnd the start end
     */
    public PlanningProjectRepositoryModel(String name, String place, String color, AdminServiceModel admin, StartEndServiceModel startEnd) {
    }
}
