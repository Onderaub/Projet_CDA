package com.CDA.PLanning.planning.repository.absence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="absence")

public class AbsenceRepositoryModel {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "absenceId")
        private Long absenceId;
        @Column(name = "name")
        private String name;

    public AbsenceRepositoryModel(String name) {
        this.name=name;
    }
}

