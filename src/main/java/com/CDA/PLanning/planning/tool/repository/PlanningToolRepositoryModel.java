package com.CDA.PLanning.planning.tool.repository;

import com.CDA.PLanning.human.personn.repository.PersonnRepositoryModel;
import com.CDA.PLanning.planning.startEnd.repository.StartEndRepositoryModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name="planningTool")

public class PlanningToolRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="idTool")
    private Long id;

    @Column(name="name")
    private String name;
    @Column(name="Quantity")
    private int quantity;
    @OneToOne(mappedBy ="planningTool")
    @JoinColumn
    private StartEndRepositoryModel StartEnd;
    @ManyToOne
    @JoinColumn(name = "personn_id")  // Remplacez "personn_id" par le nom de la colonne dans votre table d'absence qui fait référence à la personne
    private PersonnRepositoryModel personn;
}
