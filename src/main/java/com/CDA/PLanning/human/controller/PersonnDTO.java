package com.CDA.PLanning.human.controller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnDTO {
    private Long id;
    private String name;
    private String surmane;
    private String adresse;
    private String email;
    private Long phoneNumber;

}
