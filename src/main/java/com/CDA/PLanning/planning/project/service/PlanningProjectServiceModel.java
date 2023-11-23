package com.CDA.PLanning.planning.project.service;
import com.CDA.PLanning.human.admin.repository.AdminRepositoryModel;
import com.CDA.PLanning.planning.startEnd.controller.StartEndDTO;
import com.CDA.PLanning.planning.startEnd.repository.StartEndRepositoryModel;
import com.CDA.PLanning.planning.startEnd.service.StartEndService;
import com.CDA.PLanning.planning.startEnd.service.StartEndServiceModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Client service model.
 */
@Service
@Getter
@Setter
@AllArgsConstructor
@Data
public class PlanningProjectServiceModel {

    private Optional<Long> id;
    private String name;
    private String place;
    private String color;
    private AdminRepositoryModel admin;
    private List<StartEndServiceModel> startEnd;






}
