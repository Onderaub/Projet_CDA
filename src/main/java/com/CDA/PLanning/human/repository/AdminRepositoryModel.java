package com.CDA.PLanning.human.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Admin repository model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="admin")
public class AdminRepositoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idAdmin")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "e-mail")
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;




    public AdminRepositoryModel(String name, String surname, String adresse, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.adresse = adresse;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}

