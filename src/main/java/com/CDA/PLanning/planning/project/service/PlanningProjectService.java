package com.CDA.PLanning.planning.project.service;
import com.CDA.PLanning.human.admin.repository.AdminRepository;
import com.CDA.PLanning.human.personn.repository.PersonRepositoryModel;
import com.CDA.PLanning.planning.project.repository.PlanningProjectRepository;
import com.CDA.PLanning.planning.project.repository.PlanningProjectRepositoryModel;
import com.CDA.PLanning.planning.startEnd.repository.StartEndRepository;
import com.CDA.PLanning.planning.startEnd.repository.StartEndRepositoryModel;
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

    /**
     * The Planning project repository.
     */
    @Autowired
    PlanningProjectRepository planningProjectRepository;
    /**
     * The Start end repository.
     */
    @Autowired
    StartEndRepository startEndRepository;
    /**
     * The Admin repository.
     */
    @Autowired
    AdminRepository adminRepository;

    /**
     * Add boolean.
     *
     * @param planningProjectServiceModel the planning project service model
     * @return the boolean
     */
    @Transactional
    public boolean add(PlanningProjectServiceModel planningProjectServiceModel) {

        if (planningProjectServiceModel.getName() == null ||
                    planningProjectServiceModel.getPlace() == null ||
                    planningProjectServiceModel.getColor() == null ||
                    planningProjectServiceModel.getAdmin() == null ||
                    planningProjectServiceModel.getStartEnd() == null) {
            return false;

        }

        PlanningProjectRepositoryModel planningProjectRepositoryModel = new PlanningProjectRepositoryModel
                (planningProjectServiceModel.getName(),
                        planningProjectServiceModel.getPlace(),
                        planningProjectServiceModel.getColor(),
                        planningProjectServiceModel.getAdmin(),
                        planningProjectServiceModel.getStartEnd());
       var planningProjectRepositoryModelReturned = planningProjectRepository.save(planningProjectRepositoryModel);

       /*
       if(planningProjectRepositoryModelReturned == null) {
           return false;
       }

            StartEndRepositoryModel stendrepo = new StartEndRepositoryModel(
                    planningProjectServiceModel.getStartEnd().getStartDate(),
                    planningProjectServiceModel.getStartEnd().getEndDate(),
                    planningProjectRepositoryModelReturned.getId());

            startEndRepository.save(stendrepo);


            adminRepository.findById(planningProjectServiceModel.getAdmin().getId());



            System.out.println(planningProjectRepositoryModelReturned);



*/
        System.out.println(planningProjectRepositoryModelReturned);


        return planningProjectRepositoryModelReturned != null;
    }
}
