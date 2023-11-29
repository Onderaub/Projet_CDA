package com.CDA.PLanning.planning.service.tool;


import com.CDA.PLanning.planning.repository.tool.ToolRepository;
import com.CDA.PLanning.planning.repository.tool.ToolRepositoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ToolService {

    @Autowired
    private ToolRepository toolRepository;

    public boolean add(ToolServiceModel toolServiceModel) {
    // Vérifier que les propriétés nécessaires ne sont pas null
        if (toolServiceModel == null )
        {
        return false;
    }
    // Enregistre Le model du personne au format de la bdd en quantifiant les éléments Nom, prenom, etc
    ToolRepositoryModel toolRepositoryModel = new ToolRepositoryModel
            (toolServiceModel.getName());


        try {
        // Sauvegarde en base de données
        ToolRepositoryModel savedtool = toolRepository.save(toolRepositoryModel);

        // Vérifie si la sauvegarde a réussi
        return savedtool != null;
    } catch (Exception e) {
        // Gérer l'exception (log, rollback, etc.)
        e.printStackTrace();
        return false;


    }
}

    public boolean deleteToolById(Long id) {
        try {
            toolRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
