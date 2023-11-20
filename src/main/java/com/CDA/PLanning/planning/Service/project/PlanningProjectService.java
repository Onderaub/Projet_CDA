package com.CDA.PLanning.planning.Service.project;
import com.CDA.PLanning.planning.repository.project.PlanningProjectRepository;
import com.CDA.PLanning.planning.repository.project.PlanningProjectRepositoryModel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

/**
 * The type Client service.
 */
@Data
@Service
public class PlanningProjectService {

    @Autowired
    PlanningProjectRepository planningProjectRepository;

    public boolean add( PlanningProjectServiceModel planningProjectServiceModel) {

        if(planningProjectServiceModel.getStartDate() == null||
        planningProjectServiceModel.getEndDate() == null){
            return false;

        }
        PlanningProjectRepositoryModel planningProjectRepositoryModel= new PlanningProjectRepositoryModel(planningProjectServiceModel.getStartDate(),planningProjectServiceModel.getEndDate());

        PlanningProjectRepositoryModel planningProjectRepositoryModelReturned= planningProjectRepository.save(planningProjectRepositoryModel);

        return planningProjectRepositoryModelReturned !=null;
    }
}
