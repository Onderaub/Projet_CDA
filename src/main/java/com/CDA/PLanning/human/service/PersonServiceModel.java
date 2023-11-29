package com.CDA.PLanning.human.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * The type Person service model.
 */
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

    /**
     * Instantiates a new Person service model.
     *
     * @param name        the name
     * @param surname     the surname
     * @param adresse     the adresse
     * @param email       the email
     * @param phoneNumber the phone number
     */
    public PersonServiceModel(String name, String surname, String adresse, String email, Long phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.adresse = adresse;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
