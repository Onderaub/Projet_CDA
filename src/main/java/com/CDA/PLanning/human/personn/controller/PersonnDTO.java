package com.CDA.PLanning.human.personn.controller;
import lombok.Data;

import java.util.Optional;

@Data

public class PersonnDTO {
    private Optional<Long> id;
    private String name;
    private String surname;
    private String adresse;
    private String email;
    private Long phoneNumber;

}
