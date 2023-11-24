package com.CDA.PLanning.planning.startEnd.service;

import com.CDA.PLanning.planning.project.repository.PlanningProjectRepository;
import com.CDA.PLanning.planning.project.repository.PlanningProjectRepositoryModel;

import com.CDA.PLanning.planning.startEnd.repository.StartEndRepository;
import com.CDA.PLanning.planning.startEnd.repository.StartEndRepositoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;
@Service
public class StartEndService {
    @Autowired
    PlanningProjectRepository planningProjectRepository;
    @Autowired
    StartEndRepository startEndRepository;

}

