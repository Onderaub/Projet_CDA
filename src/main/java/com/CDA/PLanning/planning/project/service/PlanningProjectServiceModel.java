package com.CDA.PLanning.planning.project.service;
import com.CDA.PLanning.human.admin.repository.AdminRepositoryModel;
import com.CDA.PLanning.planning.project.repository.PlanningProjectRepository;
import com.CDA.PLanning.planning.startEnd.repository.StartEndRepositoryModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Client service model.
 */
@Service

@AllArgsConstructor
@Data
public class PlanningProjectServiceModel {

    private Optional<Long> id;
    private String name;
    private String place;
    private String color;
    private AdminRepositoryModel admin;
    private List<StartEndRepositoryModel> startEnd;
    @Autowired
    PlanningProjectRepository planningProjectRepository;

    public PlanningProjectServiceModel() {
    }

    public PlanningProjectServiceModel(String name, String place, String color, AdminRepositoryModel admin, List<StartEndRepositoryModel> startEnd) {
    }
}
