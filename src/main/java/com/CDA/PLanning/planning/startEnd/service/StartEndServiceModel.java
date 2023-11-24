package com.CDA.PLanning.planning.startEnd.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;


@Getter
@Setter
@AllArgsConstructor
@Data
public class StartEndServiceModel {
    private Optional<Long> id;
    private Date startDate;
    private Date endDate;
    private Long idProject;
    private Long idTool;
    private Long idAbsence;


}
