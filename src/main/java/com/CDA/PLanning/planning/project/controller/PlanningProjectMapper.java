package com.CDA.PLanning.planning.project.controller;


import com.CDA.PLanning.planning.project.controller.PlanningProjectDTO;
import com.CDA.PLanning.planning.project.service.PlanningProjectServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * The interface Planning project mapper.
 */
@Mapper
public interface PlanningProjectMapper {

    /**
     * The constant INSTANCE.
     */
    PlanningProjectMapper INSTANCE = Mappers.getMapper(PlanningProjectMapper.class);

    /**
     * To service model planning project service model.
     *
     * @param project the project
     * @return the planning project service model
     */
    PlanningProjectServiceModel toServiceModel(PlanningProjectDTO project);


}
