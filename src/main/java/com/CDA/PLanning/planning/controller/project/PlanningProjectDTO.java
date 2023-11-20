package com.CDA.PLanning.planning.controller.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Optional;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanningProjectDTO {
    private Optional<Long> id;
    private Date startDate;
    private Date endDate;
}
