package com.CDA.PLanning.planning.repository.absence;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
@Table(name="absence")

public class AbsenceRepositoryModel {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;
        @Column(name = "name")
        private String name;

    public AbsenceRepositoryModel(String name) {
        this.name=name;
    }



}

