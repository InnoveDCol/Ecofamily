package com.innovedcol.ecofamily.services;

import com.innovedcol.ecofamily.entities.Enterprise;

import java.util.ArrayList;

public class EnterpriseService {

    // Creamos el arraylist que contendrá las empresas
    ArrayList<Enterprise> enterprisesList = new ArrayList<>();

    public EnterpriseService() { // Constructor vacio
        this.datosIniciales(); // Se llenan los datos iniciales de las empresas
    }

    // Metodo para cargar datos iniciales de las empresas
    public void datosIniciales(){
        enterprisesList.add(new Enterprise("Ecofamily","Medellin","4561235","123456987-4"));
        enterprisesList.add(new Enterprise("AutoMantec S.A.","Barranquilla","6586452","54632178-1"));
        enterprisesList.add(new Enterprise("JZG Developers","Cartagena","6613524","1143343653-0"));
    }

    // Metodo que retorna un objeto de tipo empresa segun su index
    public Enterprise getEnterprises(int index){
        return enterprisesList.get(index);
    }

    // Metodo que retorna el arraylist de todas las empresas
    public ArrayList<Enterprise> getEnterprisesList(){
        return enterprisesList;
    }

    // Metodo que retorna la informacion de una empresa del listado segun su index
    public Enterprise searchEnterprise(int index){
        return enterprisesList.get(index);
    }

    // Metodo que crea una empresa y la añade al listado. Retorna un mensaje
    public String createEnterprise(Enterprise e){
        enterprisesList.add(e);
        return "--> Empresa creada";
    }

    // Metodo que actualiza la informacion de una empresa segun su index. Retorna un mensaje
    public String updateEnterprise(int index, Enterprise e){
        enterprisesList.set(index, e);
        return "--> Empresa actualizada";
    }

    // Metodo que elimina una empresa del listado. Retorna un mensaje
    public String deleteEnterprise(int index){
        enterprisesList.remove(index);
        return "--> Empresa eliminada";
    }

}
