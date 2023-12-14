package com.CDA.PLanning.planning.controller.project;
import lombok.*;

/**
 * The type Person dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProjectDTO {
    private Long id;
    private String name;
    private String Directeur;
    private String place;

    public ProjectDTO(Long id, String name) {
    }
}
