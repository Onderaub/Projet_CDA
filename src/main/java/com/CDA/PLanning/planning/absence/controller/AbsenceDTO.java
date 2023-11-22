package com.CDA.PLanning.planning.absence.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceDTO {
    private Optional<Long> id;
    private String reason;
    private Date date;
    private String color;
}
