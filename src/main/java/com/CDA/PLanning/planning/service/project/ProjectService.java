package com.CDA.PLanning.planning.service.project;


import com.CDA.PLanning.planning.repository.project.ProjectRepository;
import com.CDA.PLanning.planning.repository.project.ProjectRepositoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Person service.
 */

@Service
public class ProjectService {
    /**
     * The Project repository.
     */
    @Autowired
    ProjectRepository projectRepository;

    public boolean addProject(ProjectServiceModel projectServiceModel) {
        // Vérifier que les propriétés nécessaires ne sont pas null
        if (projectServiceModel.getName() == null ||
                projectServiceModel.getDirecteur() == null ||
                projectServiceModel.getPlace() == null ) {
            return false;
        }
        // Enregistre Le model du personne au format de la bdd en quantifiant les éléments Nom, prenom, etc
        ProjectRepositoryModel projectRepositoryModel = new ProjectRepositoryModel
                        (projectServiceModel.getName(),
                        projectServiceModel.getDirecteur(),
                        projectServiceModel.getPlace());


        try {
            // Sauvegarde en base de données
            ProjectRepositoryModel savedProject = projectRepository.save(projectRepositoryModel);

            // Vérifie si la sauvegarde a réussi
            return savedProject != null;
        } catch (Exception e) {
            // Gérer l'exception (log, rollback, etc.)
            e.printStackTrace();
            return false;


        }
    }

    public boolean deleteProjectById(Long id) {
        try {
            // Vérifie si le projet avec l'ID spécifié existe
            Optional<ProjectRepositoryModel> projectOptional = projectRepository.findById(id);
            if (projectOptional.isPresent()) {
                // Le projet avec l'ID spécifié a été trouvée, supprimer de la base de données
                projectRepository.deleteById(id);
                return true; // Suppression réussie
            } else {
                return false; // Le projet avec l'ID spécifié n'a pas été trouvée
            }
        } catch (Exception e) {
            // Gérer l'exception (log, rollback, etc.)
            e.printStackTrace();
            return false;
        }
    }




    public ProjectServiceModel findById(Long id) {

        Optional<ProjectRepositoryModel> projectRepositoryModel = projectRepository.findById(id);

        if(projectRepositoryModel.isEmpty())
        {
            return null;
        } else {
            return new ProjectServiceModel(
                      projectRepositoryModel.get().getName(),
                      projectRepositoryModel.get().getDirecteur(),projectRepositoryModel.get().getPlace());
        }
    }

    public ProjectServiceModel findByName(String name) {

        // Utiliser le repository pour une recherche par nom
        Optional<ProjectRepositoryModel> projectRepositoryModel = projectRepository.findByName(name);

        if (projectRepositoryModel != null) {
            // Si un nom correspondant est trouvé, le mapper vers un objet PersonServiceModel

            ProjectServiceModel projectServiceModel = new ProjectServiceModel();
            projectServiceModel.setId(Optional.ofNullable(projectRepositoryModel.get().getId()));
            projectServiceModel.setName(projectRepositoryModel.get().getName());
            projectServiceModel.setDirecteur(projectRepositoryModel.get().getDirecteur());
            projectServiceModel.setPlace(projectRepositoryModel.get().getPlace());
            System.out.println(projectServiceModel);
            return projectServiceModel;
        } else {

            return null;
        }
    }

    public boolean update(Long id, ProjectServiceModel projectServiceModel) {
        ProjectRepositoryModel projectRepositoryModel=new ProjectRepositoryModel
                (projectServiceModel.getName(),
                        projectServiceModel.getDirecteur(),
                        projectServiceModel.getPlace());

        ProjectRepositoryModel projectRepositoryModelReturned= projectRepository.save( projectRepositoryModel);
        return projectRepositoryModelReturned !=null;
    }
}




