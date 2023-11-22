package com.CDA.PLanning.human.admin;

import com.CDA.PLanning.human.personn.controller.PersonnDTO;
import com.CDA.PLanning.human.service.personn.PersonnService;
import com.CDA.PLanning.human.service.personn.PersonnServiceModel;
import com.CDA.PLanning.planning.project.service.PlanningProjectService;
import com.CDA.PLanning.planning.project.service.PlanningProjectServiceModel;
import com.CDA.PLanning.planning.project.controller.PlanningProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("admin")
@RestController

public class AdminController {
    @Autowired
    PersonnService personnService;
    @Autowired
    PlanningProjectService planningProjectService;


    @PostMapping("/newSon")
    public boolean add (@RequestBody PersonnDTO personnDTO){
        PersonnServiceModel personnServiceModel= new PersonnServiceModel
                        (personnDTO.getName(),
                        personnDTO.getSurname(),
                        personnDTO.getAdresse(),
                        personnDTO.getEmail(),
                        personnDTO.getPhoneNumber());

        return personnService.add(personnServiceModel);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean success = personnService.deleteById(id);

        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build(); // Succès (HTTP 200)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Non trouvé (HTTP 404)
        }
    }
    @PostMapping("/newProject")
    public boolean add (@RequestBody PlanningProjectDTO planningProjectDTO){

        PlanningProjectServiceModel planningprojectServiceModel= new PlanningProjectServiceModel
                (planningProjectDTO.getName(),
                 planningProjectDTO.getPlace(),
                 planningProjectDTO.getColor(),
                 planningProjectDTO.getAdmin(),
                 planningProjectDTO.getStartEnd());
        return planningProjectService.add(planningprojectServiceModel);
    }
}
