package com.CDA.PLanning.planning.controller.project;

import com.CDA.PLanning.Mapper;
import com.CDA.PLanning.exceptions.ProjectNotFoundException;
import com.CDA.PLanning.human.controller.PersonDTO;
import com.CDA.PLanning.human.service.PersonServiceModel;
import com.CDA.PLanning.planning.controller.tool.ToolDTO;
import com.CDA.PLanning.planning.repository.project.ProjectRepositoryModel;
import com.CDA.PLanning.planning.repository.tool.ToolRepositoryModel;
import com.CDA.PLanning.planning.service.project.ProjectService;
import com.CDA.PLanning.planning.service.project.ProjectServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("project")
@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @PostMapping("/add")
    public boolean add(@RequestBody ProjectDTO projectDTO) {
        ProjectServiceModel projectServiceModel= new ProjectServiceModel(projectDTO.getName(),projectDTO.getDirecteur(), projectDTO.getPlace());

        return projectService.addProject(projectServiceModel);
    }

    /**
     * Delete by id response entity.
     *
     * @param
     * @return the response entity
     */
    @GetMapping()
    public ResponseEntity<ProjectDTO> findByName(@RequestParam String name) {
        // Appeler le service pour rechercher la projet par nom
        ProjectServiceModel foundProject = projectService.findByName(name);

        if (foundProject != null) {
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setId(foundProject.getId().get());
            projectDTO.setName(foundProject.getName());
            projectDTO.setDirecteur(foundProject.getDirecteur());
            projectDTO.setPlace(foundProject.getPlace());
            ;
           // projectDTO.setImage(foundProject.getImage());



            return new ResponseEntity<>(projectDTO, HttpStatus.OK);
        } else {
            // Gérez le cas où aucune personne avec le nom spécifié n'a été trouvé
            // Vous pouvez choisir de renvoyer une réponse HTTP 404 ou un message d'erreur approprié.
            return ResponseEntity.notFound().build(); // Réponse HTTP 404
        }
    }

    @GetMapping("/{id}")
    public ProjectDTO findById(@PathVariable Long id) throws ProjectNotFoundException {
        ProjectDTO projectDTO = new ProjectDTO();

            ProjectServiceModel projectServiceModel = projectService.findById(id);
            projectDTO.setId(id);
            projectDTO.setName(projectServiceModel.getName());
            projectDTO.setDirecteur(projectServiceModel.getDirecteur());
            projectDTO.setPlace(projectServiceModel.getPlace());


            return new ResponseEntity<>(projectDTO, HttpStatus.OK).getBody();



        }

        @DeleteMapping("/del/{id}")

        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
            boolean success = projectService.deleteProjectById(id);

            if (success) {
                return ResponseEntity.status(HttpStatus.OK).build();
                // Succès (HTTP 200)
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Non trouvé (HTTP 404)
            }
        }
    @PutMapping("up/{id}")
    public boolean updateById(@RequestBody ProjectDTO projectDTO, @PathVariable Long id) {    // lis id dans l'url
        ProjectServiceModel projectServiceModel = new ProjectServiceModel             (projectDTO.getName(),projectDTO.getDirecteur(),projectDTO.getPlace()
                );
        return projectService.update(id,projectServiceModel);
    }

    @GetMapping("/all")
    public ArrayList<ProjectDTO> findAll(){

        ArrayList<ProjectDTO> projectDTOs= new ArrayList<>();
        ArrayList<ProjectRepositoryModel> projectServiceModels = projectService.findAllProject();
        projectServiceModels.forEach((item)->projectDTOs.add(new ProjectDTO(item.getId(),item.getName(),item.getDirecteur(),item.getPlace())));
        return projectDTOs;
    }
    }


