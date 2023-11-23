package com.CDA.PLanning.human.admin;

import com.CDA.PLanning.human.personn.controller.PersonnDTO;
import com.CDA.PLanning.human.service.personn.PersonnService;
import com.CDA.PLanning.human.service.personn.PersonnServiceModel;
import com.CDA.PLanning.planning.project.controller.PlanningProjectDTO;
import com.CDA.PLanning.planning.project.service.PlanningProjectService;
import com.CDA.PLanning.planning.project.service.PlanningProjectServiceModel;
import com.CDA.PLanning.human.personn.controller.PersonnMapper;
import com.CDA.PLanning.planning.project.controller.PlanningProjectMapper;
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
    public boolean addPerson(@RequestBody PersonnDTO personnDTO) {
        PersonnServiceModel personnServiceModel = PersonnMapper.INSTANCE.toServiceModel(personnDTO);
        return personnService.add(personnServiceModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean success = personnService.deleteById(id);

        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build(); // Success (HTTP 200)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Not found (HTTP 404)
        }
    }

    @PostMapping("/newProject")
    public boolean addProject(@RequestBody PlanningProjectDTO planningProjectDTO) {
        PlanningProjectServiceModel planningprojectServiceModel = PlanningProjectMapper.INSTANCE.toServiceModel(planningProjectDTO);
        return planningProjectService.add(planningprojectServiceModel);
    }
}
