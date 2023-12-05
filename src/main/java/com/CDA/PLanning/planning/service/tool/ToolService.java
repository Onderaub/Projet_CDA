package com.CDA.PLanning.planning.service.tool;



import com.CDA.PLanning.human.repository.PersonRepositoryModel;
import com.CDA.PLanning.planning.repository.tool.ToolRepository;
import com.CDA.PLanning.planning.repository.tool.ToolRepositoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

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
    // Enregistre Le model de l'outils au format de la bdd en quantifiant les éléments Nom
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
    public ToolServiceModel findByName(String name) {

        // Utiliser le repository pour une recherche par nom
        Optional<ToolRepositoryModel> toolRepositoryModel = toolRepository.findByName(name);

        if (toolRepositoryModel != null) {
            // Si un nom correspondant est trouvé, le mapper vers un objet ToolServiceModel

            ToolServiceModel toolServiceModel = new ToolServiceModel();
            toolServiceModel.setId(Optional.ofNullable(toolRepositoryModel.get().getId()));
            toolServiceModel.setName(toolRepositoryModel.get().getName());

            System.out.println(toolServiceModel);
            return toolServiceModel;
        } else {

            return null;
        }
    }
    public boolean update(Long id, ToolServiceModel toolServiceModel) {

        ToolRepositoryModel toolRepositoryModel=new ToolRepositoryModel

                (id, toolServiceModel.getName());

        ToolRepositoryModel toolRepositoryModelReturned= toolRepository.save( toolRepositoryModel);
        return toolRepositoryModelReturned !=null;
    }

    public ToolServiceModel findById(Long id) {
        Optional<ToolRepositoryModel> toolRepositoryModel = toolRepository.findById(id);

        if(toolRepositoryModel.isEmpty())
        {
            return null;
        } else {
            return new ToolServiceModel(
                    toolRepositoryModel.get().getName());
        }
    }

    public ArrayList<ToolRepositoryModel> findAllTool() {
        ArrayList<ToolRepositoryModel> toolServiceModels= new ArrayList<>();

        ArrayList<ToolRepositoryModel>ToolRepositorieModels= (ArrayList<ToolRepositoryModel>) toolRepository.findAll();
        ToolRepositorieModels.forEach((item)->toolServiceModels.add(new ToolRepositoryModel
                (item.getId(),
                        item.getName())));
        return toolServiceModels;
    }
}
