package com.CDA.PLanning.human.controller;

import com.CDA.PLanning.Mapper;
import com.CDA.PLanning.human.service.PersonService;
import com.CDA.PLanning.human.service.PersonServiceModel;
import com.CDA.PLanning.planning.controller.absence.AbsenceDTO;
import com.CDA.PLanning.planning.service.absence.AbsenceServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("person")
@RestController
public class PersonController {
    @Autowired
    PersonService personService;
    @PostMapping("/add")
    public boolean addPerson(@RequestBody PersonDTO personDTO) {
        PersonServiceModel personServiceModel = Mapper.INSTANCE.toPersonServiceModel(personDTO);
        return personService.addPerson(personServiceModel);
    }

    /**
     * Delete by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable Long id) {
        boolean success = personService.deletePersonById(id);

        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build(); // Success (HTTP 200)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Not found (HTTP 404)
        }
    }
    @PutMapping("/{id}")
    public boolean updateById(@RequestBody PersonDTO personDTO, @PathVariable Long id) {    // lis id dans l'url

        PersonServiceModel personServiceModel = new PersonServiceModel(personDTO.getName(),personDTO.getSurname(),personDTO.getAdresse(),personDTO.getEmail(),personDTO.getPhoneNumber());
        return personService.update(id, personServiceModel);

    }
}
