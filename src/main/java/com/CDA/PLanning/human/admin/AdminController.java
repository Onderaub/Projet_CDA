package com.CDA.PLanning.human.admin;

import com.CDA.PLanning.human.personn.controller.PersonDTO;
import com.CDA.PLanning.human.service.personn.PersonService;
import com.CDA.PLanning.human.service.personn.PersonServiceModel;
import com.CDA.PLanning.planning.project.controller.PlanningProjectDTO;
import com.CDA.PLanning.planning.project.service.PlanningProjectService;
import com.CDA.PLanning.planning.project.service.PlanningProjectServiceModel;
import com.CDA.PLanning.human.personn.controller.PersonMapper;
import com.CDA.PLanning.planning.project.controller.PlanningProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("admin")
@RestController
public class AdminController {
    @Autowired
    PersonService personService;

    @Autowired
    PlanningProjectService planningProjectService;

    @PostMapping("/newSon")
    public boolean addPerson(@RequestBody PersonDTO personDTO) {
        PersonServiceModel personServiceModel = PersonMapper.INSTANCE.toServiceModel(personDTO);
        return personService.add(personServiceModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean success = personService.deleteById(id);

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
