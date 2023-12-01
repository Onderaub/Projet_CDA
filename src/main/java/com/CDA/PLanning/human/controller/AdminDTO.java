package com.CDA.PLanning.human.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Data
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

}
