package com.CDA.PLanning.planning.repository.project;
import com.CDA.PLanning.planning.repository.startEnd.StartEndRepositoryModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.Manager;

import java.sql.Date;
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
            @Column (name="planningProjectId")
            private Long id;
            @Column(name="name")
            private String name;
             @Column(name="place")
            private String place;
            @OneToOne
            @PrimaryKeyJoinColumn
            private Manager manager;
             @Column (name="color")
            private String color;
             @OneToMany (mappedBy ="planningProject")
             @PrimaryKeyJoinColumn
    private List<StartEndRepositoryModel> StartEnd;


    public PlanningProjectRepositoryModel(Date startDate, Date endDate) {
    }
}
