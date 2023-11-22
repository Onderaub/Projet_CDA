package com.CDA.PLanning.planning.project.service;
import com.CDA.PLanning.planning.project.repository.PlanningProjectRepository;
import com.CDA.PLanning.planning.project.repository.PlanningProjectRepositoryModel;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Client service.
 */
@Data
@Service
public class PlanningProjectService {

    @Autowired
    PlanningProjectRepository planningProjectRepository;
    @Transactional
    public boolean add( PlanningProjectServiceModel planningProjectServiceModel) {

        if(planningProjectServiceModel.getName() == null||
           planningProjectServiceModel.getPlace() == null||
           planningProjectServiceModel.getColor() == null||
           planningProjectServiceModel.getAdmin() == null||
           planningProjectServiceModel.getStartEnd() == null)
        {
            return false;

        }
        PlanningProjectRepositoryModel planningProjectRepositoryModel= new PlanningProjectRepositoryModel
                (planningProjectServiceModel.getName(),
                 planningProjectServiceModel.getPlace(),
                 planningProjectServiceModel.getColor(),
                 planningProjectServiceModel.getAdmin(),
                 planningProjectServiceModel.getStartEnd());

        PlanningProjectRepositoryModel planningProjectRepositoryModelReturned= planningProjectRepository.save(planningProjectRepositoryModel);

        return planningProjectRepositoryModelReturned !=null;
    }
}
