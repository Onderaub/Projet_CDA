package com.CDA.PLanning.planning.Service.project;
import com.CDA.PLanning.planning.repository.project.PlanningProjectRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

/**
 * The type Client service model.
 */
@Service
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanningProjectServiceModel {

    private Optional<Long>id;
    private Date StartDate;
    private Date EndDate;

    @Autowired
    PlanningProjectRepository planningProjectRepository;

    public PlanningProjectServiceModel(Date startDate, Date endDate) {
    }
}
