package com.CDA.PLanning.planning.project.repository;
import com.CDA.PLanning.human.admin.repository.AdminRepositoryModel;
import com.CDA.PLanning.planning.startEnd.controller.StartEndDTO;
import com.CDA.PLanning.planning.startEnd.repository.StartEndRepositoryModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

            @OneToMany(mappedBy = "project")
            private List<StartEndRepositoryModel> startEnd;
            @OneToOne
            @PrimaryKeyJoinColumn
            private AdminRepositoryModel admin;




    public PlanningProjectRepositoryModel(String name, String place, String color, AdminRepositoryModel admin, List<StartEndRepositoryModel> startEnd) {
        this.name=name;
        this.place=place;
        this.color=color;
        this.startEnd=startEnd;
        this.admin=admin;

    }

    public PlanningProjectRepositoryModel(String name, String place, String color) {
        this.name= name;
        this.place= place;
        this.color= color;
    }
}
