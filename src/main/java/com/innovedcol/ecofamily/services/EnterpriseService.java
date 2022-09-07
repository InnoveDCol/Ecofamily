package com.innovedcol.ecofamily.services;

import com.innovedcol.ecofamily.entities.Enterprise;
import com.innovedcol.ecofamily.repositories.EnterpriseRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EnterpriseService {

    // Definimos un atributo de tipo repositorio
    private final EnterpriseRepository repository;

    public EnterpriseService(EnterpriseRepository repository) {
        this.repository = repository;
    }

    // Método que retorna un arraylist con el listado de las empresas
    public ArrayList<Enterprise> getEnterprisesList(){
        return (ArrayList<Enterprise>) repository.findAll();
    }

    // Método que retorna un objeto de tipo Enterprise según su ID
    public Optional<Enterprise> searchEnterprise(Long id){
        return repository.findById(id);
    }

    // Método que crea una empresa y la añade a la base de datos. Retorna un mensaje
    public String createEnterprise(Enterprise e){
        if(searchEnterprise(e.getId()).isEmpty()){
            repository.save(e);
            return "--> Empresa creada con éxito!";
        }else{
            return "--> Empresa ya existe!";
        }
    }

    // Método que actualiza la información de una empresa según su id. Retorna un mensaje
    public String updateEnterprise(Long id, Enterprise e){
        if(searchEnterprise(id).isPresent()){
            repository.save(e);
            return "--> Empresa actualizada con éxito!";
        }else{
            return "--> La empresa indicada no existe!";
        }
    }

    // Método que elimina una empresa de la base de datos. Retorna un mensaje
    public String deleteEnterprise(Long id){
        if(searchEnterprise(id).isPresent()){
            repository.deleteById(id);
            return "--> Empresa eliminada con éxito!";
        }else{
            return "--> La empresa indicada no existe!";
        }
    }
}