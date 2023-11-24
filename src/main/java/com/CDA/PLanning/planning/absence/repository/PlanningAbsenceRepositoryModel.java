package com.CDA.PLanning.planning.absence.repository;

import com.CDA.PLanning.human.personn.repository.PersonRepositoryModel;
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
    @Column(name="reason")
    private String reason;
    @Column(name="color")
    private String color;
    @Column(name="idPerson",insertable=false, updatable=false)
    private Long idPersonn;

    @OneToMany(mappedBy = "absence")
    private List<StartEndRepositoryModel> dates;
    @ManyToOne
    @JoinColumn(name = "idPerson")
    private PersonRepositoryModel person;
}
