package com.CDA.PLanning.planning.absence.repository;

import com.CDA.PLanning.human.personn.repository.PersonnRepositoryModel;
import com.CDA.PLanning.planning.startEnd.repository.StartEndRepositoryModel;
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
    @JoinColumn(name = "idPersonn")
    private PersonnRepositoryModel personn;

    @Column(name="reason")
    private String reason;
    @OneToMany(mappedBy = "absence")
    private List<StartEndRepositoryModel> dates;
    @Column(name="color")
    private String color;

}
