package com.CDA.PLanning.planning.repository.startEnd;
import com.CDA.PLanning.planning.repository.project.PlanningProjectRepository;
import com.CDA.PLanning.planning.repository.tool.PlanningToolRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;



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
     @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="startEndId")
    private Long id;
    @Column (name="start")
    private Date startDate;
    @Column (name="End")
    private Date endDate;
    @OneToOne
    @JoinColumn(name = "idTool")
    private PlanningToolRepository tool;
    @ManyToOne
    @JoinColumn(name = "idProject")
    private PlanningProjectRepository project;
}
