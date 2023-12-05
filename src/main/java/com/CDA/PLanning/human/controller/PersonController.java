package com.CDA.PLanning.human.controller;

import com.CDA.PLanning.Mapper;
import com.CDA.PLanning.exceptions.PersonNotFoundException;
import com.CDA.PLanning.human.service.AdminServiceModel;
import com.CDA.PLanning.human.service.PersonService;
import com.CDA.PLanning.human.service.PersonServiceModel;
import com.CDA.PLanning.planning.service.project.ProjectServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("person")
@RestController
public class PersonController {
    @Autowired
    PersonService personService;
    @PostMapping("/add")
    public boolean addPerson(@RequestBody PersonDTO personDTO) {
        PersonServiceModel personServiceModel= new PersonServiceModel(personDTO.getName(),personDTO.getSurname(), personDTO.getAdresse(),personDTO.getEmail(),personDTO.getPhoneNumber());
        return personService.addPerson(personServiceModel);
    }

    /**
     * Delete by id response entity.
     *
     *
     * @return the response entity
     */
    @GetMapping()
    public ResponseEntity<PersonDTO> findByName(@RequestParam String name) {
        // Appeler le service pour rechercher la personne par nom
        PersonServiceModel foundPerson = personService.findByName(name);

        if (foundPerson != null) {
            PersonDTO personDTO = new PersonDTO();
            personDTO.setId(foundPerson.getId().get());
            personDTO.setName(foundPerson.getName());
            personDTO.setSurname(foundPerson.getSurname());
            personDTO.setAdresse(foundPerson.getAdresse());
            personDTO.setEmail(foundPerson.getEmail());
            personDTO.setPhoneNumber(foundPerson.getPhoneNumber());
           // personDTO.setImage(foundPerson.getImage());



            return new ResponseEntity<>(personDTO, HttpStatus.OK);
        } else {
            // Gérez le cas où aucune personne avec le nom spécifié n'a été trouvé
            // Vous pouvez choisir de renvoyer une réponse HTTP 404 ou un message d'erreur approprié.
            return ResponseEntity.notFound().build(); // Réponse HTTP 404
        }
    }
    @PutMapping("up/{id}")
    public boolean updateById(@RequestBody PersonDTO personDTO, @PathVariable Long id) {    // lis id dans l'url

        PersonServiceModel personServiceModel = new PersonServiceModel             (personDTO.getName(),
                personDTO.getSurname(),
                personDTO.getAdresse(),
                personDTO.getEmail(),
                personDTO.getPhoneNumber());
        return personService.update(id,personServiceModel);
    }
    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException{
        PersonDTO personDTO = new PersonDTO();

            PersonServiceModel personServiceModel = personService.findById(id);
            personDTO.setId(id);
            personDTO.setName(personServiceModel.getName());
            personDTO.setSurname(personServiceModel.getSurname());
            personDTO.setAdresse(personServiceModel.getAdresse());
            personDTO.setEmail(personDTO.getEmail());
            personDTO.setPhoneNumber(personDTO.getPhoneNumber());

            return new ResponseEntity<>(personDTO, HttpStatus.OK).getBody();



        }

        @DeleteMapping("/del/{id}")

        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
            boolean success = personService.deletePersonById(id);

            if (success) {
                return ResponseEntity.status(HttpStatus.OK).build();
                // Succès (HTTP 200)
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Non trouvé (HTTP 404)
            }
        }
    }


