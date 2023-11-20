package com.CDA.PLanning.human.service.personn;

import com.CDA.PLanning.human.repository.personn.PersonnRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonnServiceModel {
    private Optional<Long> id;
    private String name;
    private String surname;
    private String adresse;
    private String email;
    private Long phoneNumber;
    @Autowired
    PersonnRepository personnRepository;
    public PersonnServiceModel(String name, String surmane, String adresse, String email, Long phoneNumber) {
    }


    public PersonnServiceModel(Optional<Long> id, String name, String adresse, String email, Long phoneNumber) {
    }
}
