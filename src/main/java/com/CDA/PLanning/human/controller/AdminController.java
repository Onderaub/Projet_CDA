package com.CDA.PLanning.human.controller;

import com.CDA.PLanning.human.service.personn.PersonnService;
import com.CDA.PLanning.human.service.personn.PersonnServiceModel;
import com.CDA.PLanning.planning.Service.project.PlanningProjectService;
import com.CDA.PLanning.planning.Service.project.PlanningProjectServiceModel;
import com.CDA.PLanning.planning.controller.project.PlanningProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("admin")
@RestController

public class AdminController {
    @Autowired
    PersonnService personnService;
    @Autowired
    PlanningProjectService planningProjectService;


    @PostMapping("/newSon")
    public boolean add (@RequestBody PersonnDTO personnDTO){
        PersonnServiceModel personnServiceModel= new PersonnServiceModel(personnDTO.getName(),personnDTO.getSurmane(),personnDTO.getAdresse(),personnDTO.getEmail(),personnDTO.getPhoneNumber());return personnService.add(personnServiceModel);

    }
    @PostMapping("/newProject")
    public boolean add (@RequestBody PlanningProjectDTO planningProjectDTO){
        PlanningProjectServiceModel planningprojectServiceModel= new PlanningProjectServiceModel(planningProjectDTO.getStartDate(),planningProjectDTO.getEndDate());
        return planningProjectService.add(planningprojectServiceModel);
    }
}
