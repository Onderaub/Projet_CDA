package com.CDA.PLanning.planning.service.absence;

import com.CDA.PLanning.planning.repository.absence.AbsenceRepository;
import com.CDA.PLanning.planning.repository.absence.AbsenceRepositoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

@Service
public class AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    public boolean add(AbsenceServiceModel absenceServiceModel) {
        // Vérifier que les propriétés nécessaires ne sont pas null
        if (absenceServiceModel == null )
        {
            return false;
        }
        // Enregistre Le model de l'absence au format de la bdd en quantifiant les éléments
        AbsenceRepositoryModel absenceRepositoryModel = new AbsenceRepositoryModel
                (absenceServiceModel.getName());


        try {
            // Sauvegarde en base de données
            AbsenceRepositoryModel savedtool = absenceRepository.save(absenceRepositoryModel);

            // Vérifie si la sauvegarde a réussi
            return savedtool != null;
        } catch (Exception e) {
            // Gérer l'exception (log, rollback, etc.)
            e.printStackTrace();
            return false;


        }
    }

    public boolean deleteAbsenceById(Long id) {
        try {
            absenceRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    @PutMapping("/{id}")
    public boolean update(Long id, AbsenceServiceModel absenceServiceModel) {

        AbsenceRepositoryModel absenceRepositoryModel=new AbsenceRepositoryModel( absenceServiceModel.getName());

        AbsenceRepositoryModel absenceRepositoryModelReturned= absenceRepository.save( absenceRepositoryModel);
        return absenceRepositoryModelReturned !=null;
    }
}

