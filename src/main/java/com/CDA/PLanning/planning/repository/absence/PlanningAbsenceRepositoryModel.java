package com.CDA.PLanning.planning.repository.absence;

import com.CDA.PLanning.human.repository.personn.PersonnRepositoryModel;
import com.CDA.PLanning.planning.repository.startEnd.StartEndRepositoryModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="absence")
public class PlanningAbsenceRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idAbsence")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "personn_id")  // Remplacez "personn_id" par le nom de la colonne dans votre table d'absence qui fait référence à la personne
    private PersonnRepositoryModel personn;
    @Column(name="reason")
    private String reason;
    @OneToMany (mappedBy = "Absence")
    private List<StartEndRepositoryModel> dates;
    @Column(name="color")
    private String color;

}
