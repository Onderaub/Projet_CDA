package com.CDA.PLanning.human.service.admin;

import com.CDA.PLanning.human.service.personn.PersonServiceModel;
import lombok.*;

import java.util.Optional;

/**
 * The type Admin service model.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminServiceModel extends PersonServiceModel {
    private Optional<Long> id;
    private Long  personId;
}
