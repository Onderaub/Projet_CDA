package com.CDA.PLanning;

import com.CDA.PLanning.human.controller.AdminDTO;
import com.CDA.PLanning.human.repository.AdminRepositoryModel;
import com.CDA.PLanning.human.controller.PersonDTO;
import com.CDA.PLanning.human.service.PersonServiceModel;
import com.CDA.PLanning.planning.controller.project.ProjectDTO;
import com.CDA.PLanning.planning.service.project.ProjectServiceModel;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

public interface Mapper {
    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

    // Mappings for Person
    @Mapping(target = "id", ignore=true)
    PersonServiceModel toPersonServiceModel(PersonDTO personDTO);

    // Mappings for Admin
    @Mapping(target = "personId", source = "personId")
    AdminRepositoryModel toAdminRepositoryModel(AdminDTO adminDTO);


    default Optional<Long> map(Long value) {
        return Optional.ofNullable(value);
    }

    default Long map(Optional<Long> value) {
        return value.orElse(null);
    }

    @Mapping(target = "id", ignore=true)
    ProjectServiceModel toProjectServiceModel(ProjectDTO projectDTO);
}
