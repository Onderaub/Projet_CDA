package com.CDA.PLanning.planning.service.project;

import lombok.*;

import java.util.Optional;


/**
 * The type Person service model.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class ProjectServiceModel {
    private Optional<Long> id;
    private String name;
    private String directeur;
    private String place;



    public ProjectServiceModel(String name, String directeur, String place) {
        this.name = name;
        this.directeur = directeur;
        this.place = place;

    }
}
