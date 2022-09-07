package com.innovedcol.ecofamily.services;

import com.innovedcol.ecofamily.entities.Profile;
import com.innovedcol.ecofamily.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProfileService {
    // Definimos un atributo de tipo repositorio
    private final ProfileRepository repository;

    // Constructor
    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    // Método que retorna un arraylist con el listado de los perfiles
    public ArrayList<Profile> getProfilesList(){
        return (ArrayList<Profile>) repository.findAll();
    }

    // Método que retorna un objeto de tipo Profile según su ID
    public Optional<Profile> searchProfile(String id){
        return repository.findById(id);
    }

    // Método que crea un perfil y lo añade a la base de datos. Retorna un mensaje
    public String createProfile(Profile p){
        repository.save(p);
        return "--> El perfil con ID " + p.getId() + " fue creado satisfactoriamente!";
    }

    // Método que actualiza la información de un perfil según su id. Retorna un mensaje
    public String updateProfile(String id, Profile p){
        //TODO: Completar esta parte del codigo
        return "--> El perfil con ID " + p.getId() + " fue actualizado satisfactoriamente!";
    }

    // Método que elimina una transacción de la base de datos. Retorna un mensaje
    public String deleteProfile(String id){
        repository.deleteById(id);
        return "--> El perfil con ID " + id + " fue eliminado satisfactoriamente!";
    }
}