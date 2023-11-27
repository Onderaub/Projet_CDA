package com.CDA.PLanning.planning.startEnd.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Optional;

/**
 * The type Start end dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartEndDTO {
    private Optional<Long> id;
    private Date startDate;
    private Date endDate;
    private Long idProject;
    private Long idTool;
    private Long idAbsence;
}
