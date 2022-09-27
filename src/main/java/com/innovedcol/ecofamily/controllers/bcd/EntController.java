package com.innovedcol.ecofamily.controllers.bcd;

import com.innovedcol.ecofamily.entities.Enterprise;
import com.innovedcol.ecofamily.services.backend.EnterpriseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EntController {

    //ATRIBUTOS
    private final EnterpriseService enterpriseService;

    //MÉTODOS

    // Método para llamar al servicio que retorna el arraylist de todos los empleados:
    @GetMapping("/enterprises")
    public List<?> EnterprisesList () {
        return this.enterpriseService.getEnterprisesList();
    }

    //CRUD:

    // Buscar: Método para llamar al servicio que busca una empresa de acuerdo a su id:
    @GetMapping("/enterprise/{id}")
    public Optional<Enterprise> searchEnterprise(@PathVariable("id") Long id){
        return this.enterpriseService.searchEnterprise(id);
    }
    // Método para llamar al servicio que busca las transacciones de una empresa de acuerdo a su id:
    @GetMapping("/enterprise/{id}/movements")
    public List<?> searchTransactionsEnterprise(@PathVariable("id") Long id){
        return this.enterpriseService.searchTransactionsEnterprise(id);
    }

    // Método para llamar al servicio que busca los empleados de una empresa de acuerdo a su id:
    @GetMapping("/enterprise/{id}/users")
    public List<?> searchEmployeesEnterprise(@PathVariable("id") Long id){
        return this.enterpriseService.searchEmployeesEnterprise(id);
    }

    // Insertar: Método para llamar al servicio que crea una nueva empresa:
    @PostMapping("/enterprises")
    public String createEnterprise (@RequestBody Enterprise e) {
        return this.enterpriseService.createEnterprise(e);
    }

    // Actualizar: Método para llamar al servicio que actualiza la información de una empresa:
    @PatchMapping("/enterprise/{id}")
    public String updateEnterprise(@PathVariable("id") Long id, @RequestBody Enterprise e) {
        return this.enterpriseService.updateEnterprise(id,e);
    }

    // Eliminar: Método para llamar al servicio que elimina una empresa:
    @DeleteMapping("/enterprise/{id}")
    public String deleteEnterprise(@PathVariable("id") Long id) {
        return this.enterpriseService.deleteEnterprise(id); }

}