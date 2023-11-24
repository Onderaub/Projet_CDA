package com.CDA.PLanning.planning.startEnd.repository;
import com.CDA.PLanning.planning.absence.repository.PlanningAbsenceRepositoryModel;
import com.CDA.PLanning.planning.project.repository.PlanningProjectRepositoryModel;
import com.CDA.PLanning.planning.tool.repository.PlanningToolRepositoryModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Optional;


/**
 * The type Client repository model.
 */

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name="startEnd")
public class StartEndRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "startEndId")
    private Long id;
    @Column(name = "start")
    private Date startDate;
    @Column(name = "End")
    private Date endDate;
    @Column(name = "idProject", insertable = false, updatable = false)
    private Long idProject;
    @Column(name = "idTool", insertable = false, updatable = false)
    private Long idTool;
    @Column(name = "idAbsence", insertable = false, updatable = false)
    private Long idAbsence;


    @OneToOne
    @JoinColumn(name = "idTool", insertable = false, updatable = false)
    private PlanningToolRepositoryModel planningTool;
    @ManyToOne
    @JoinColumn(name = "idProject")
    private PlanningProjectRepositoryModel project;
    @ManyToOne
    @JoinColumn(name = "idAbsence")
    private PlanningAbsenceRepositoryModel absence;

    public StartEndRepositoryModel(Date startDate, Date endDate, Long idProject) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.idProject = idProject;
    }


}
