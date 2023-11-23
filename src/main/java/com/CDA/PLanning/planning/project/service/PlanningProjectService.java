package com.CDA.PLanning.planning.project.service;
import com.CDA.PLanning.human.admin.repository.AdminRepository;
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

    @Autowired
    PlanningProjectRepository planningProjectRepository;
    @Autowired
    StartEndRepository startEndRepository;
    @Autowired
    AdminRepository adminRepository;
    @Transactional
    public boolean add( PlanningProjectServiceModel planningProjectServiceModel) {

        if (planningProjectServiceModel.getName() == null ||
                    planningProjectServiceModel.getPlace() == null ||
                    planningProjectServiceModel.getColor() == null ||
                    planningProjectServiceModel.getAdmin() == null ||
                    planningProjectServiceModel.getStartEnd() == null) {
            return false;

        }

        PlanningProjectRepositoryModel planningProjectRepositoryModelReturned = null;
        // Créé une boucle pour la list StartEnd
        for (var x : planningProjectServiceModel.getStartEnd()) {
            PlanningProjectRepositoryModel planningProjectRepositoryModel = new PlanningProjectRepositoryModel
                    (planningProjectServiceModel.getName(),
                     planningProjectServiceModel.getPlace(),
                     planningProjectServiceModel.getColor());
            planningProjectRepositoryModelReturned = planningProjectRepository.save(planningProjectRepositoryModel);
            StartEndRepositoryModel stendrepo = new StartEndRepositoryModel(
                    x.getStartDate(),
                    x.getEndDate(),
                    planningProjectRepositoryModelReturned.getId());

            startEndRepository.save(stendrepo);


            System.out.println(planningProjectRepositoryModelReturned);
        }

        return planningProjectRepositoryModelReturned != null;
    }
}
