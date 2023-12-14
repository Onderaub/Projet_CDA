package com.CDA.PLanning.human.service;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * The type Person service model.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class PersonServiceModel {
    private Optional<Long> id;
    private String name;
    private String surname;
    private String adresse;
    private String email;
    private String phoneNumber;

    /**
     * Instantiates a new Person service model.
     *
     * @param name        the name
     * @param surname     the surname
     * @param adresse     the adresse
     * @param email       the email
     * @param phoneNumber the phone number
     */
    public PersonServiceModel(String name, String surname, String adresse, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.adresse = adresse;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
