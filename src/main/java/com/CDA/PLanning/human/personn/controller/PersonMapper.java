package com.CDA.PLanning.human.personn.controller;
import com.CDA.PLanning.human.admin.AdminDTO;
import com.CDA.PLanning.human.service.personn.PersonServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The interface Person mapper.
 */
@Mapper
public interface PersonMapper {
    /**
     * The constant INSTANCE.
     */
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);


    /**
     * To service model person service model.
     *
     * @param person the person
     * @return the person service model
     */
    PersonServiceModel toServiceModel(PersonDTO person);
    PersonServiceModel toServiceModel(AdminDTO personID);

}