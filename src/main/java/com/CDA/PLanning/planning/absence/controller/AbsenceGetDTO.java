package com.CDA.PLanning.planning.absence.controller;

import java.sql.Date;


/**
 * The type Absence get dto.
 */
public record AbsenceGetDTO(Long id, String reason, Date date, String color) {
}
