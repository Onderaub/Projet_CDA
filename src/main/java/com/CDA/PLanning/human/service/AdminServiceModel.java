package com.CDA.PLanning.human.service;

import lombok.*;

import java.util.Optional;

/**
 * The type Admin service model.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminServiceModel extends PersonServiceModel {
    private Optional<Long> id;
    private String name;
    private String surname;
    private String adresse;
    private String email;
    private String phoneNumber;




    public AdminServiceModel(String name, String surname, String adresse, String email, String phoneNumber) {
        this.name=name;
        this.surname= surname;
        this.adresse=adresse;
        this.email=email;
        this.phoneNumber= phoneNumber;
    }
}
