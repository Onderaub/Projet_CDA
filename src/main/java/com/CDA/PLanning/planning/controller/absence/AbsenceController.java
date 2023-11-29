package com.CDA.PLanning.planning.controller.absence;


import com.CDA.PLanning.planning.service.absence.AbsenceService;
import com.CDA.PLanning.planning.service.absence.AbsenceServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("absence")
    @RestController
    public class AbsenceController {

    @Autowired
    AbsenceService absenceService;

    @PostMapping("/add")
    public boolean add(@RequestBody AbsenceDTO absenceDTO) {
        AbsenceServiceModel absenceServiceModel = new AbsenceServiceModel(absenceDTO.getName());
        return absenceService.add(absenceServiceModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = absenceService.deleteAbsenceById(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public boolean updateById(@RequestBody AbsenceDTO absenceDTO, @PathVariable Long id) {    // lis id dans l'url

        AbsenceServiceModel absenceServiceModel = new AbsenceServiceModel(absenceDTO.getName());
        return absenceService.update(id, absenceServiceModel);

    }
    }

