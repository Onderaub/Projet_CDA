package com.CDA.PLanning.planning.service.tool;

import lombok.*;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ToolServiceModel {
    private Optional<Long> id;
    private String name;

    public ToolServiceModel(String name) {
        this.name=name;
    }
}


