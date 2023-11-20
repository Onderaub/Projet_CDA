package com.CDA.PLanning.planning.controller.absence;

import com.CDA.PLanning.planning.repository.absence.PlanningAbsenceRepositoryModel;

import java.sql.Date;


public record AbsenceGetDTO(Long id, String reason, Date date, String color) {
}
