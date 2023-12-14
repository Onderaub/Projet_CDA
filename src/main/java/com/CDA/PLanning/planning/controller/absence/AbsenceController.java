package com.CDA.PLanning.planning.controller.absence;


import com.CDA.PLanning.planning.controller.tool.ToolDTO;
import com.CDA.PLanning.planning.repository.absence.AbsenceRepositoryModel;
import com.CDA.PLanning.planning.service.absence.AbsenceService;
import com.CDA.PLanning.planning.service.absence.AbsenceServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


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
    @GetMapping()
    public ResponseEntity<AbsenceDTO> findByName(@RequestParam String name) {
        // Appeler le service pour rechercher la personne par nom
        AbsenceServiceModel foundAbsence = absenceService.findByName(name);

        if (foundAbsence != null) {
            AbsenceDTO absenceDTO = new AbsenceDTO();
            absenceDTO.setId(foundAbsence.getId().get());
            absenceDTO.setName(foundAbsence.getName());
            // personDTO.setImage(foundPerson.getImage());



            return new ResponseEntity<>(absenceDTO, HttpStatus.OK);
        } else {
            // Gérez le cas où aucune personne avec le nom spécifié n'a été trouvé
            // Vous pouvez choisir de renvoyer une réponse HTTP 404 ou un message d'erreur approprié.
            return ResponseEntity.notFound().build(); // Réponse HTTP 404
        }
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
    @GetMapping("/all")
    public ArrayList<AbsenceDTO> findAll(){

        ArrayList<AbsenceDTO> absenceDTOs= new ArrayList<>();
        ArrayList<AbsenceRepositoryModel> absenceServiceModels = absenceService.findAllAbsence();
        absenceServiceModels.forEach((item)->absenceDTOs.add(new AbsenceDTO(item.getId(),item.getName())));
        return absenceDTOs;
    }
    }

