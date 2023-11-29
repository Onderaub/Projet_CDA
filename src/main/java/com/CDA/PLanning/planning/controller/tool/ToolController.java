package com.CDA.PLanning.planning.controller.tool;


import com.CDA.PLanning.planning.service.tool.ToolService;
import com.CDA.PLanning.planning.service.tool.ToolServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
