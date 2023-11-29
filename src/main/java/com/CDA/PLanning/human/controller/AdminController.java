package com.CDA.PLanning.human.controller;

import com.CDA.PLanning.Mapper;
import com.CDA.PLanning.human.service.AdminService;
import com.CDA.PLanning.human.service.AdminServiceModel;
import com.CDA.PLanning.human.service.PersonServiceModel;



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
    AdminService adminService;
    /**
     * The Planning project service.
     */


    /**
     * Add person boolean.
     *
     * @param personDTO the person dto
     * @return the boolean
     */


    /**
     * Add project boolean.
     *
     * @return the boolean
     */
  /*  @PostMapping("/newProject")
    public boolean addProject(@RequestBody PlanningProjectDTO planningProjectDTO) {
        PlanningProjectServiceModel planningprojectServiceModel = Mapper.INSTANCE.toPlanningProjectServiceModel(planningProjectDTO);
        return planningProjectService.add(planningprojectServiceModel);
    }
*/
    @PostMapping("/add")
    public boolean add(@RequestBody AdminDTO adminDTO){
        AdminServiceModel adminServiceModel= new AdminServiceModel( adminDTO.getName(),adminDTO.getSurname(), adminDTO.getAdresse(), adminDTO.getEmail(),adminDTO.getPhoneNumber());
        return adminService.addAdmin(adminServiceModel);
    }


}
