package com.CDA.PLanning.planning.project.controller;

import com.CDA.PLanning.human.admin.repository.AdminRepositoryModel;
import com.CDA.PLanning.human.personn.controller.PersonDTO;
import com.CDA.PLanning.human.personn.repository.PersonRepositoryModel;
import com.CDA.PLanning.planning.startEnd.controller.StartEndDTO;
import com.CDA.PLanning.planning.startEnd.repository.StartEndRepositoryModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * The type Planning project dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanningProjectDTO {
    private Optional<Long> id;
    private String name;
    private String place;
    private String color;
    private AdminRepositoryModel admin;
    private StartEndDTO startEnd;
}
