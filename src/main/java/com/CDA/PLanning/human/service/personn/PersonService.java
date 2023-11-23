package com.CDA.PLanning.human.service.personn;

import com.CDA.PLanning.human.personn.repository.PersonRepository;
import com.CDA.PLanning.human.personn.repository.PersonRepositoryModel;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;
    @Transactional
//********************************************PersonnService
        public boolean add(PersonServiceModel personServiceModel) {
            // Vérifier que les propriétés nécessaires ne sont pas null
            if (personServiceModel.getName() == null ||
                    personServiceModel.getSurname() == null ||
                    personServiceModel.getAdresse() == null ||
                    personServiceModel.getEmail() == null ||
                    personServiceModel.getPhoneNumber() == null) {
                return false;
            }
        // Enregistre Le model du personn au format de la bdd en quantifiant les éléments Nom, prenom, etc
        PersonRepositoryModel personRepositoryModel= new PersonRepositoryModel
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

    public boolean deleteById(Long id) {

            Optional<PersonRepositoryModel> personRepositoryModel =
                    personRepository.findById(id);

            if (personRepositoryModel.isPresent()) {
                // L'objet personn avec l'ID spécifié a été trouvé, supprimerle de la base de données
                personRepository.deleteById(id);
                return true; // Suppression réussie
            } else {
                return false; // L'objet avec l'ID spécifié n'a pas été trouvé

        }
    }

}


