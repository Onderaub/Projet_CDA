package com.CDA.PLanning.human.service.personn;

import com.CDA.PLanning.human.repository.personn.PersonnRepository;
import com.CDA.PLanning.human.repository.personn.PersonnRepositoryModel;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Data
@Service
public class PersonnService {
    @Autowired
    PersonnRepository personnRepository;
    @Transactional

        public boolean add(PersonnServiceModel personnServiceModel) {
            // Vérifier que les propriétés nécessaires ne sont pas null
            if (personnServiceModel.getName() == null ||
                    personnServiceModel.getSurname() == null ||
                    personnServiceModel.getAdresse() == null ||
                    personnServiceModel.getEmail() == null ||
                    personnServiceModel.getPhoneNumber() == null) {
                return false;
            }
        // Enregistre Le model du personn au format de la bdd en quantifiant les éléments Nom, prenom, etc
        PersonnRepositoryModel personnRepositoryModel= new PersonnRepositoryModel
                (personnServiceModel.getName(),
                personnServiceModel.getSurname(),
                personnServiceModel.getAdresse(),
                personnServiceModel.getEmail(),
                personnServiceModel.getPhoneNumber());


        try {
            // Sauvegarde en base de données
            PersonnRepositoryModel savedPerson = personnRepository.save(personnRepositoryModel);

            // Vérifie si la sauvegarde a réussi
            return savedPerson != null;
        } catch (Exception e) {
            // Gérer l'exception (log, rollback, etc.)
            e.printStackTrace();
            return false;

        }
}}
