package com.CDA.PLanning.planning.project.service;
import com.CDA.PLanning.human.admin.repository.AdminRepositoryModel;
import com.CDA.PLanning.human.personn.repository.PersonRepositoryModel;
import com.CDA.PLanning.human.service.admin.AdminServiceModel;
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
@Getter
@Setter
@AllArgsConstructor
@Data
public class PlanningProjectServiceModel {

    private Optional<Long> id;
    private String name;
    private String place;
    private String color;
    private List<PersonRepositoryModel> person;
    private AdminServiceModel admin;
    private StartEndServiceModel startEnd;

}
