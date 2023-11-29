package com.CDA.PLanning.human.service;

import com.CDA.PLanning.human.repository.AdminRepository;
import com.CDA.PLanning.human.repository.AdminRepositoryModel;
import com.CDA.PLanning.human.repository.PersonRepository;
import com.CDA.PLanning.human.repository.PersonRepositoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
