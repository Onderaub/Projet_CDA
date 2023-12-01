package com.CDA.PLanning.human.service;

import com.CDA.PLanning.exceptions.PersonNotFoundException;
import com.CDA.PLanning.human.controller.AdminDTO;
import com.CDA.PLanning.human.controller.PersonDTO;
import com.CDA.PLanning.human.repository.AdminRepository;
import com.CDA.PLanning.human.repository.AdminRepositoryModel;
import com.CDA.PLanning.human.repository.PersonRepository;
import com.CDA.PLanning.human.repository.PersonRepositoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
       if (adminServiceModel.getName() == null ||
               adminServiceModel.getSurname() == null ||
               adminServiceModel.getAdresse() == null ||
               adminServiceModel.getEmail() == null ||
               adminServiceModel.getPhoneNumber() == null) {
           return false;
       }
       // Enregistre Le model du personne au format de la bdd en quantifiant les éléments Nom, prenom, etc
       AdminRepositoryModel adminRepositoryModelReturned = new AdminRepositoryModel
                       (adminServiceModel.getName(),
                       adminServiceModel.getSurname(),
                       adminServiceModel.getAdresse(),
                       adminServiceModel.getEmail(),
                       adminServiceModel.getPhoneNumber());

       try {
           // Je sauvegarde l'administrateur en base de données
           AdminRepositoryModel savedAdmin = adminRepository.save(adminRepositoryModelReturned);

           // Vérifie si la sauvegarde a réussi
           return savedAdmin != null;
       } catch (Exception e) {
           // Gérer l'exception (log, rollback, etc.)
           e.printStackTrace();
           return false;}
   }
    public AdminServiceModel findById(Long id) {

        Optional<AdminRepositoryModel> adminRepositoryModel = adminRepository.findById(id);

        if(adminRepositoryModel.isEmpty())
        {
            return null;
        } else {
            return new AdminServiceModel(adminRepositoryModel.get().getName(),adminRepositoryModel.get().getSurname(),adminRepositoryModel.get().getAdresse(),adminRepositoryModel.get().getEmail(),adminRepositoryModel.get().getPhoneNumber());
        }
    }


}
