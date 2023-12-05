package com.CDA.PLanning.human.controller;

import com.CDA.PLanning.Mapper;
import com.CDA.PLanning.exceptions.AdminNotFoundException;
import com.CDA.PLanning.exceptions.PersonNotFoundException;
import com.CDA.PLanning.human.repository.AdminRepositoryModel;
import com.CDA.PLanning.human.service.AdminService;
import com.CDA.PLanning.human.service.AdminServiceModel;
import com.CDA.PLanning.human.service.PersonServiceModel;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * The type Admin controller.
 */
@RequestMapping("admin")
@RestController
public class AdminController {
    /**
     * The Person service.
     */

    @Autowired
    AdminService adminService;
    /**
     * The Planning project service.
     */


    /**
     * Add person boolean.
     *
     * @param personDTO the person dto
     * @return the boolean
     */


    /**
     * Add project boolean.
     *
     * @return the boolean
     */
  /*  @PostMapping("/newProject")
    public boolean addProject(@RequestBody PlanningProjectDTO planningProjectDTO) {
        PlanningProjectServiceModel planningprojectServiceModel = Mapper.INSTANCE.toPlanningProjectServiceModel(planningProjectDTO);
        return planningProjectService.add(planningprojectServiceModel);
    }
*/
    @PostMapping("/add")
    public boolean add(@RequestBody AdminDTO adminDTO){
        AdminServiceModel adminServiceModel= new AdminServiceModel( adminDTO.getName(),adminDTO.getSurname(), adminDTO.getAdresse(), adminDTO.getEmail(),adminDTO.getPhoneNumber());
        return adminService.addAdmin(adminServiceModel);
    }
    @GetMapping("/{id}")
    public AdminDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        AdminDTO adminDTO = new AdminDTO();

        AdminServiceModel adminServiceModel = adminService.findById(id);
        adminDTO.setId(Optional.ofNullable(id));
        adminDTO.setName(adminServiceModel.getName());
        adminDTO.setSurname(adminServiceModel.getSurname());
        adminDTO.setAdresse(adminServiceModel.getAdresse());
        adminDTO.setEmail(adminServiceModel.getEmail());
        adminDTO.setPhoneNumber(adminDTO.getPhoneNumber());

        return new ResponseEntity<>(adminDTO, HttpStatus.OK).getBody();



    }
    @PutMapping("up/{id}")
    public boolean updateById(@RequestBody AdminDTO adminDTO, @PathVariable Long id) {    // lis id dans l'url
        AdminServiceModel adminServiceModel = new AdminServiceModel             (adminDTO.getName(),
                adminDTO.getSurname(),
                adminDTO.getAdresse(),
                adminDTO.getEmail(),
                adminDTO.getPhoneNumber());
        return adminService.update(id,adminServiceModel);
    }


    @DeleteMapping("/del/{id}")

    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean success = adminService.deleteAdminById(id);

        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
            // Succès (HTTP 200)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Non trouvé (HTTP 404)
        }

    }
    @GetMapping("/all")
    public ArrayList<AdminDTO> findAll(){

        ArrayList<AdminDTO> adminDTOs= new ArrayList<>();
        ArrayList<AdminRepositoryModel> adminServiceModels = adminService.findAll();
        adminServiceModels.forEach((item)->adminDTOs.add(new AdminDTO(item.getId(),item.getName(),item.getSurname(), item.getAdresse(),item.getEmail(),item.getPhoneNumber())));
        return adminDTOs;
    }
}
