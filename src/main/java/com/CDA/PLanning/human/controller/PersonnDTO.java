package com.CDA.PLanning.human.controller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
