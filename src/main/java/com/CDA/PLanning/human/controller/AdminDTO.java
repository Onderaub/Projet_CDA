package com.CDA.PLanning.human.controller;

import lombok.*;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
/**
 * The type Admin dto.
 */
public class AdminDTO {
    private Optional<Long> id;
    private String name;
    private String surname;
    private String adresse;
    private String email;
    private String phoneNumber;

    public AdminDTO(Long id, String name, String surname, String adresse, String email, String phoneNumber) {
        this.id = id.describeConstable();
        this.name = name;
        this.surname = surname;
        this.adresse = adresse;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
