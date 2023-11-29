package com.CDA.PLanning.planning.controller.absence;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class AbsenceDTO {
    private Long absenceId;
    private String name;

}
