package com.CDA.PLanning.human.service.personn;

import com.CDA.PLanning.human.personn.repository.PersonnRepository;
import com.CDA.PLanning.human.personn.repository.PersonnRepositoryModel;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class PersonnService {
    @Autowired
    PersonnRepository personnRepository;
    @Transactional
//********************************************PersonnService
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
}

    public boolean deleteById(Long id) {

            Optional<PersonnRepositoryModel> personnRepositoryModel =
                    personnRepository.findById(id);

            if (personnRepositoryModel.isPresent()) {
                // L'objet personn avec l'ID spécifié a été trouvé, supprimerle de la base de données
                personnRepository.deleteById(id);
                return true; // Suppression réussie
            } else {
                return false; // L'objet avec l'ID spécifié n'a pas été trouvé

        }
    }

}


