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
        if(searchProfile(p.getId()).isEmpty()){
            repository.save(p);
            return "--> Perfil creado con exito!";
        }else{
            return "--> Perfil ya existe!";
        }
    }

    // Método que actualiza la información de un perfil según su id. Retorna un mensaje
    public String updateProfile(String id, Profile p){
        if(searchProfile(id).isPresent()){
            repository.save(p);
            return "--> Perfil actualizado con exito!";
        }else{
            return "--> El perfil indicado no existe!";
        }
    }

    // Método que elimina un perfil de la base de datos. Retorna un mensaje
    public String deleteProfile(String id){
        if(searchProfile(id).isPresent()){
            repository.deleteById(id);
            return "--> Perfil eliminado con exito!";
        }else{
            return "--> El perfil indicado no existe!";
        }

    }
}