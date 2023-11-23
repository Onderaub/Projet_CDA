package com.CDA.PLanning.planning.project.controller;


import com.CDA.PLanning.planning.project.controller.PlanningProjectDTO;
import com.CDA.PLanning.planning.project.service.PlanningProjectServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlanningProjectMapper {

    PlanningProjectMapper INSTANCE = Mappers.getMapper(PlanningProjectMapper.class);

    PlanningProjectServiceModel toServiceModel(PlanningProjectDTO project);


}
