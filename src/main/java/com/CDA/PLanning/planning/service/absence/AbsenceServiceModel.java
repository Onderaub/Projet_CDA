package com.CDA.PLanning.planning.service.absence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceServiceModel {

    private Optional<Long> id;
    private String name;

    public AbsenceServiceModel(String name) {
        this.name=name;
    }


    public AbsenceServiceModel(String name, String surname, String adresse, String email, Long phoneNumber) {
    }
}
