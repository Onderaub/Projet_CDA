package com.CDA.PLanning.human.service.admin;

import com.CDA.PLanning.human.admin.repository.AdminRepository;
import com.CDA.PLanning.human.admin.repository.AdminRepositoryModel;
import com.CDA.PLanning.human.personn.repository.PersonRepository;
import com.CDA.PLanning.human.personn.repository.PersonRepositoryModel;
import com.CDA.PLanning.human.service.personn.PersonService;
import com.CDA.PLanning.human.service.personn.PersonServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Admin service.
 */
@Service
public class AdminService  extends PersonService {


        @Autowired
        private AdminRepository adminRepository;

        @Autowired
        private PersonRepository personRepository;

    /**
     * Add admin boolean.
     *

     * @return the boolean
     */

   public boolean addAdmin(AdminServiceModel adminServiceModel) {
        // Je vérifie si la personne avec l'ID spécifié existe
        Long personId = adminServiceModel.getPersonId();
        Optional<PersonRepositoryModel> personOptional = personRepository.findById(personId);

        if (personOptional.isPresent()) {
            // Je Crée un nouvel administrateur en associant la personne existante
            AdminRepositoryModel admin = new AdminRepositoryModel();
            admin.setIdPerson(personId);
            // Je sauvegarde l'administrateur en base de données
            adminRepository.save(admin);
            return true;
        } else {
            return false; // La personne avec l'ID spécifié n'a pas été trouvée
        }
    }

}
