package com.CDA.PLanning.planning.startEnd.controller;

import java.sql.Date;

public record StartEndGetDTO (long id, Date startDate, Date endDate, long idProject, Long idTool, Long idAbsence){
}
