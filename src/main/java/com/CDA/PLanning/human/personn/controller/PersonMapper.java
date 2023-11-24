package com.CDA.PLanning.human.personn.controller;
import com.CDA.PLanning.human.service.personn.PersonServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);


    PersonServiceModel toServiceModel(PersonDTO person);


}