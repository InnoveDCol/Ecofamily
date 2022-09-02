package com.innovedcol.ecofamily;

public class Main {
    public static void main(String[] args) {
        //TEST//

        //Clase empleado:
        System.out.println("\n-- Test de clase Empleado");
        Empleado emp= new Empleado("Mario","XXXX@gmail.com","EcoFamily","Almacenista");
        System.out.println(emp.toString());

        emp.setNombre("Hellder");
        emp.setCorreo("cccc@gmail.com");
        emp.setRol("Contador");
        emp.setEmpresaContratante("EcoFamily2");
        System.out.println(emp.toString()); //Cambios de datos.

        // CLase MovimientoDinero
        System.out.println("\n-- Test de clase MovimientoDinero");
        MovimientoDinero mov = new MovimientoDinero(20000,"Consignacion inicial", "consignacion", "Hellder");
        System.out.println(mov); // Se imprimen los datos del objeto mov

        // Lectura y modificacion de monto
        System.out.println("Monto inicial: " + mov.getMonto());
        mov.setMonto(50000); // Monto Positivo
        mov.setMonto(-20000); // Monto Negativo
        System.out.println("Monto modificado: " + mov.getMonto());

        // Lectura y modificacion de concepto del movimiento
        System.out.println("Concepto inicial: " + mov.getConcepto());
        mov.setConcepto("Retiro de $20000");
        System.out.println("Concepto modificado: " + mov.getConcepto());

        // Lectura y modificacion de usuario encargado de registrar el movimiento
        System.out.println("Usuario inicial: " + mov.getUsuario());
        mov.setUsuario("Jeyson");
        System.out.println("Usuario modificado: " + mov.getUsuario());

        // Se imprime la informacion completa con los cambios realizados
        System.out.println(mov);

        //Clase empresa:
        System.out.println("\n-- Test de clase empresa");
        Empresa Emp= new Empresa("CAfe","Calle 98c #06-7","3015900465","1234");
        System.out.println(Emp.toString());

        //prueba de los setters:
        Emp.setNombre("Panadería");
        Emp.setDireccion("CAlle98_34-02");
        Emp.setTelefono("30259066");
        Emp.setNit("222999");
        System.out.println(Emp.toString());



    }
}
