package com.CDA.PLanning.planning.controller.tool;

import lombok.*;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ToolDTO {
    private Long id;
    private String name;


    public static void setId(Optional<Long> id) {
    }
}
