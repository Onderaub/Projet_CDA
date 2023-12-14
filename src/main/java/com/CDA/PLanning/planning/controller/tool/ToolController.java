package com.CDA.PLanning.planning.controller.tool;


import com.CDA.PLanning.exceptions.ToolNotFoundException;
import com.CDA.PLanning.planning.repository.tool.ToolRepositoryModel;
import com.CDA.PLanning.planning.service.tool.ToolService;
import com.CDA.PLanning.planning.service.tool.ToolServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RequestMapping("tool")
@RestController
public class ToolController {

    @Autowired
    ToolService toolService;
    @PostMapping("/add")
    public boolean add(@RequestBody ToolDTO toolDTO){
        ToolServiceModel toolServiceModel= new ToolServiceModel( toolDTO.getName());
        return toolService.add(toolServiceModel);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = toolService.deleteToolById(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping()
    public ResponseEntity<ToolDTO> findByName(@RequestParam String name) {
        // Appeler le service pour rechercher l outils par nom
        ToolServiceModel foundTool = toolService.findByName(name);

        if (foundTool != null) {
            ToolDTO toolDTO = new ToolDTO();
            ToolDTO.setId(foundTool.getId());
            toolDTO.setName(foundTool.getName());

            // personDTO.setImage(foundPerson.getImage());



            return new ResponseEntity<>(toolDTO, HttpStatus.OK);
        } else {
            // Gérez le cas où aucune personne avec le nom spécifié n'a été trouvé
            // Vous pouvez choisir de renvoyer une réponse HTTP 404 ou un message d'erreur approprié.
            return ResponseEntity.notFound().build(); // Réponse HTTP 404
        }
    }
    @PutMapping("up/{id}")
    public boolean updateById(@RequestBody ToolDTO toolDTO, @PathVariable Long id) {    // lis id dans l'url

        ToolServiceModel toolServiceModel = new ToolServiceModel             (toolDTO.getName());
        return toolService.update(id,toolServiceModel);
    }
    @GetMapping("/{id}")
    public ToolDTO findById(@PathVariable Long id) throws ToolNotFoundException {
        ToolDTO toolDTO = new ToolDTO();

        ToolServiceModel toolServiceModel = toolService.findById(id);
        toolDTO.setId(Optional.ofNullable(id));
        toolDTO.setName(toolServiceModel.getName());


        return new ResponseEntity<>(toolDTO, HttpStatus.OK).getBody();



    }
    @GetMapping("/all")
    public ArrayList<ToolDTO> findAll(){

        ArrayList<ToolDTO> toolDTOs= new ArrayList<>();
        ArrayList<ToolRepositoryModel> toolServiceModels = toolService.findAllTool();
        toolServiceModels.forEach((item)->toolDTOs.add(new ToolDTO(item.getId(),item.getName())));
        return toolDTOs;
    }
}
