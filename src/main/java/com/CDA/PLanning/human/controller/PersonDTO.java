package com.CDA.PLanning.human.controller;
import lombok.*;

import java.util.Optional;

/**
 * The type Person dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PersonDTO {
    private Long id;
    private String name;
    private String surname;
    private String adresse;
    private String email;
    private String phoneNumber;
}
