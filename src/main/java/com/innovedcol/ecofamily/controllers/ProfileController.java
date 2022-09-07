package com.innovedcol.ecofamily.controllers;

import com.innovedcol.ecofamily.entities.Profile;
import com.innovedcol.ecofamily.services.ProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ProfileController {

    // Declaramos un objeto de tipo ProfileService
    ProfileService service;

    // Constructor
    public ProfileController(ProfileService service) {
        this.service = service;
    }

    // Método para llamar al servicio que muestra el listado de perfiles
    @GetMapping("/profiles")
    public ArrayList<Profile> ProfileList(){
        return this.service.getProfilesList();
    }

    // Método para llamar al servicio que crea un nuevo perfil
    @PostMapping("/profiles")
    public String createProfile(@RequestBody Profile p){
        return this.service.createProfile(p);
    }

    // Método para llamar al servicio que busca un perfil según su id
    @GetMapping("/profiles/{id}")
    public Optional<Profile> searchProfile(@PathVariable("id") String id){
        return this.service.searchProfile(id);
    }

    // Método para llamar al servicio que actualiza la info de un perfil
    @PutMapping("/profiles/{id}")
    public String updateProfile(@PathVariable("id") String id, @RequestBody Profile p){
        return this.service.updateProfile(id,p);
    }

    // Método para llamar al servicio que eliminar un perfil
    @DeleteMapping("/profiles/{id}")
    public String deleteProfile(@PathVariable("id") String id){
        return this.service.deleteProfile(id);
    }
}
