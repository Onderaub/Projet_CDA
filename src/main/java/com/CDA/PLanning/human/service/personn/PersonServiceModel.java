package com.CDA.PLanning.human.service.personn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonServiceModel {
    private Optional<Long> id;
    private String name;
    private String surname;
    private String adresse;
    private String email;
    private Long phoneNumber;

    public PersonServiceModel(String name, String surname, String adresse, String email, Long phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.adresse = adresse;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
