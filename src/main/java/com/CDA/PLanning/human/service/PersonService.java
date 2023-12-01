package com.CDA.PLanning.human.service;

import com.CDA.PLanning.exceptions.PersonNotFoundException;
import com.CDA.PLanning.human.repository.PersonRepository;

import com.CDA.PLanning.human.repository.PersonRepositoryModel;
import com.CDA.PLanning.planning.repository.absence.AbsenceRepositoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

/**
 * The type Person service.
 */

@Service
public class PersonService {
    /**
     * The Person repository.
     */
    @Autowired
    PersonRepository personRepository;

    public boolean addPerson(PersonServiceModel personServiceModel) {
        // Vérifier que les propriétés nécessaires ne sont pas null
        if (personServiceModel.getName() == null ||
                personServiceModel.getSurname() == null ||
                personServiceModel.getAdresse() == null ||
                personServiceModel.getEmail() == null ||
                personServiceModel.getPhoneNumber() == null) {
            return false;
        }
        // Enregistre Le model du personne au format de la bdd en quantifiant les éléments Nom, prenom, etc
        PersonRepositoryModel personRepositoryModel = new PersonRepositoryModel
                (personServiceModel.getName(),
                        personServiceModel.getSurname(),
                        personServiceModel.getAdresse(),
                        personServiceModel.getEmail(),
                        personServiceModel.getPhoneNumber());


        try {
            // Sauvegarde en base de données
            PersonRepositoryModel savedPerson = personRepository.save(personRepositoryModel);

            // Vérifie si la sauvegarde a réussi
            return savedPerson != null;
        } catch (Exception e) {
            // Gérer l'exception (log, rollback, etc.)
            e.printStackTrace();
            return false;


        }
    }

    public boolean deletePersonById(Long id) {

        try {

            // L'objet personne avec l'ID spécifié a été trouvé, supprimerle de la base de données
            personRepository.deleteById(id);
            return true; // Suppression réussie
        }
        catch (Exception e) {
            System.out.println(e);
            return false; // L'objet avec l'ID spécifié n'a pas été trouvé

        }
    }




    public PersonServiceModel findById(Long id) {

        Optional<PersonRepositoryModel> personRepositoryModel = personRepository.findById(id);

        if(personRepositoryModel.isEmpty())
        {
            return null;
        } else {
            return new PersonServiceModel(
                     personRepositoryModel.get().getName(),
                     personRepositoryModel.get().getSurname(),personRepositoryModel.get().getAdresse(),personRepositoryModel.get().getEmail(),personRepositoryModel.get().getPhoneNumber());
        }
    }

    public PersonServiceModel findByName(String name) {
        // Utiliser le repository pour rechercher le DVD par nom
        Optional<PersonRepositoryModel> personRepositoryModel = personRepository.findByName(name);

        if (personRepositoryModel != null) {
            // Si un DVD correspondant est trouvé, mapper-le vers un objet DvdServiceModel
            PersonServiceModel personServiceModel = new PersonServiceModel();
            personServiceModel.setId(Optional.ofNullable(personRepositoryModel.get().getId()));
            personServiceModel.setName(personRepositoryModel.get().getName());
            personServiceModel.setSurname(personRepositoryModel.get().getSurname());
            personServiceModel.setAdresse(personRepositoryModel.get().getAdresse());
            personServiceModel.setEmail(personRepositoryModel.get().getEmail());
            personServiceModel.setPhoneNumber(personRepositoryModel.get().getPhoneNumber());
            System.out.println(personServiceModel);
            return personServiceModel;
        } else {

            return null;
        }
    }

    public boolean update(Long id, PersonServiceModel personServiceModel) {
        PersonRepositoryModel personRepositoryModel=new PersonRepositoryModel(personServiceModel.getName(), personServiceModel.getSurname(), personServiceModel.getAdresse(), personServiceModel.getEmail(),personServiceModel.getPhoneNumber());

        PersonRepositoryModel personRepositoryModelReturned= personRepository.save( personRepositoryModel);
        return personRepositoryModelReturned !=null;
    }
}




