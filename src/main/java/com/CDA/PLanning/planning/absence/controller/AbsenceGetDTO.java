package com.CDA.PLanning.planning.absence.controller;

import java.sql.Date;


public record AbsenceGetDTO(Long id, String reason, Date date, String color) {
}
