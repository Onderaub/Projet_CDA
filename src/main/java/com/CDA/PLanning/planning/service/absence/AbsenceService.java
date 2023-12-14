package com.CDA.PLanning.planning.service.absence;


import com.CDA.PLanning.planning.repository.absence.AbsenceRepository;
import com.CDA.PLanning.planning.repository.absence.AbsenceRepositoryModel;
import com.CDA.PLanning.planning.repository.tool.ToolRepositoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.Optional;

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

        AbsenceRepositoryModel absenceRepositoryModel=new AbsenceRepositoryModel( id, absenceServiceModel.getName());

        AbsenceRepositoryModel absenceRepositoryModelReturned= absenceRepository.save( absenceRepositoryModel);
        return absenceRepositoryModelReturned !=null;
    }

    public AbsenceServiceModel findByName(String name) {
        // Utiliser le repository pour une recherche par nom
        Optional<AbsenceRepositoryModel> absenceRepositoryModel = absenceRepository.findByName(name);

        if (absenceRepositoryModel != null) {
            // Si un nom correspondant est trouvé, le mapper vers un objet PersonServiceModel

            AbsenceServiceModel absenceServiceModel = new AbsenceServiceModel();
            absenceServiceModel.setId(Optional.ofNullable(absenceRepositoryModel.get().getId()));
            absenceServiceModel.setName(absenceRepositoryModel.get().getName());

            System.out.println(absenceServiceModel);
            return absenceServiceModel;
        } else {

            return null;
        }
    }
    public ArrayList<AbsenceRepositoryModel> findAllAbsence() {
        ArrayList<AbsenceRepositoryModel> absenceServiceModels= new ArrayList<>();

        ArrayList<AbsenceRepositoryModel> AbsenceRepositoryModels= (ArrayList<AbsenceRepositoryModel>) absenceRepository.findAll();
        AbsenceRepositoryModels.forEach((item)->absenceServiceModels.add(new AbsenceRepositoryModel
                (item.getId(),
                        item.getName())));
        return absenceServiceModels;
    }
}

