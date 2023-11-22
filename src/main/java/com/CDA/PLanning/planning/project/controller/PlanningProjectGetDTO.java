package com.CDA.PLanning.planning.project.controller;

import com.CDA.PLanning.human.admin.repository.AdminRepositoryModel;
import com.CDA.PLanning.planning.startEnd.repository.StartEndRepositoryModel;

import java.util.List;

public record PlanningProjectGetDTO(long id, String name, String place, String color, AdminRepositoryModel adminDate, List<StartEndRepositoryModel> startEnd) {
}
