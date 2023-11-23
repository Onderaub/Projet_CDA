package com.CDA.PLanning.human.personn.controller;
import com.CDA.PLanning.human.personn.controller.PersonnDTO;
import com.CDA.PLanning.human.service.personn.PersonnServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonnMapper {
    PersonnMapper INSTANCE = Mappers.getMapper(PersonnMapper.class);


    PersonnServiceModel toServiceModel(PersonnDTO personn);

    // Add other mappings if needed
}