package com.CDA.PLanning.human.admin;

import com.CDA.PLanning.human.personn.controller.PersonDTO;
import com.CDA.PLanning.human.service.admin.AdminService;
import com.CDA.PLanning.human.service.admin.AdminServiceModel;
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

/**
 * The type Admin controller.
 */
@RequestMapping("admin")
@RestController
public class AdminController {
    /**
     * The Person service.
     */
    @Autowired
    PersonService personService;
    @Autowired
    AdminService adminService;
    /**
     * The Planning project service.
     */
    @Autowired
    PlanningProjectService planningProjectService;

    /**
     * Add person boolean.
     *
     * @param personDTO the person dto
     * @return the boolean
     */
    @PostMapping("/newSon")
    public boolean addPerson(@RequestBody PersonDTO personDTO) {
        PersonServiceModel personServiceModel = PersonMapper.INSTANCE.toServiceModel(personDTO);
        return personService.add(personServiceModel);
    }

    /**
     * Delete by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean success = personService.deleteById(id);

        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build(); // Success (HTTP 200)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Not found (HTTP 404)
        }
    }

    /**
     * Add project boolean.
     *
     * @param planningProjectDTO the planning project dto
     * @return the boolean
     */
    @PostMapping("/newProject")
    public boolean addProject(@RequestBody PlanningProjectDTO planningProjectDTO) {
        PlanningProjectServiceModel planningprojectServiceModel = PlanningProjectMapper.INSTANCE.toServiceModel(planningProjectDTO);
        return planningProjectService.add(planningprojectServiceModel);
    }

    @PostMapping("/newAdmin")

    public boolean add(@RequestBody AdminDTO adminDTO) {
        AdminServiceModel adminServiceModel = (AdminServiceModel) PersonMapper.INSTANCE.toServiceModel(adminDTO);
        return adminService.addAdmin(adminServiceModel);
    }


}
