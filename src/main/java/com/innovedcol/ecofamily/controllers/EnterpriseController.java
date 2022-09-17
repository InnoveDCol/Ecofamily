package com.innovedcol.ecofamily.controllers;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.entities.Enterprise;
import com.innovedcol.ecofamily.services.EnterpriseService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
@RestController
public class EnterpriseController {

    //ATRIBUTOS

    private EnterpriseService enterpriseService;

    //MÉTODOS

    //Constructor:
    public EnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    // Método para llamar al servicio que retorna el arraylist de todos los empleados:
    @GetMapping("/enterprises")
    public ArrayList<Enterprise> EnterprisesList () {
        return this.enterpriseService.getEnterprisesList();
    }

    //CRUD:

    // Buscar: Método para llamar al servicio que busca un empleado de acuerdo a su id:
    @GetMapping("/enterprise/{id}")
    public Optional<Enterprise> searchEnterprise(@PathVariable("id") Long id){
        return this.enterpriseService.searchEnterprise(id);
    }

    // Insertar: Método para llamar al servicio que crea un nuevo empleado:
    @PostMapping("/enterprises")
    public String createEnterprise (@RequestBody Enterprise e) {
        return this.enterpriseService.createEnterprise(e);
    }

    // Actualizar: Método para llamar al servicio que actualiza la información de un employee:
    @PatchMapping("/enterprise/{id}")
    public String updateEnterprise(@PathVariable("id") Long id, @RequestBody Enterprise e) {
        return this.enterpriseService.updateEnterprise(id,e);
    }

    // Eliminar: Método para llamar al servicio que elimina un perfil:
    @DeleteMapping("/enterprise/{id}")
    public String deleteEnterprise(@PathVariable("id") Long id) {
        return this.enterpriseService.deleteEnterprise(id); }

}
