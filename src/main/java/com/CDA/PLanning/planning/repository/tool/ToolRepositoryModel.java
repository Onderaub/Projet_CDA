package com.CDA.PLanning.planning.repository.tool;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="tool")
public class ToolRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toolId")
    private Long toolId;
    @Column(name = "name")
    private String name;

    public ToolRepositoryModel(String name) {
        this.name=name;
    }
}
