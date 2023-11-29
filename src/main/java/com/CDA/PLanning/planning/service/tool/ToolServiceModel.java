package com.CDA.PLanning.planning.service.tool;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ToolServiceModel {
    private Long toolId;
    private String name;

    public ToolServiceModel(String name) {
        this.name = name;
    }
}
