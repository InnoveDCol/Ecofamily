package com.innovedcol.ecofamily;

import com.innovedcol.ecofamily.entities.Employee;
import com.innovedcol.ecofamily.entities.Enterprise;
import com.innovedcol.ecofamily.entities.Transaction;

import java.util.Scanner;

public class Main {

    // Definicion de una Enterprise principal
    static Enterprise enterprisePpal = new Enterprise("Ecofamily","Cra 59C # 51 - 2","6666666","789654123");
    // Definicion de un Employee administrado
    static Employee employeeAdmin = new Employee("Juan Esteban", "juanesteban@ecofamily.com", enterprisePpal,"Admin");
    // Definicion de un Employee operario
    static Employee employeeOper = new Employee("Andrea", "andrea@ecofamily.com", enterprisePpal,"Operario");

    public static void old_main() {


        // Definicion de variable para el Scanner
        Scanner sc = new Scanner(System.in);
        int opcion; // Variable que guardara las opciones del menu

        do {
            System.out.println("\n========================================");
            System.out.println("\t--------- MENU TESTER --------- ");
            System.out.println("========================================");
            System.out.println("1. Prueba clase Enterprise");
            System.out.println("2. Prueba clase Employee");
            System.out.println("3. Prueba clase Transaction");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion para continuar: ");
            opcion = sc.nextInt();


            switch (opcion) {
                case 1:
                    testerEmpresa();
                    break;
                case 2:
                    testerEmpleado();
                    break;
                case 3:
                    testerMovimientoDinero();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Error!! Opción incorrecta");
            }
        } while (opcion != 4);
    }

    private static void testerEmpresa() {
        //Clase Enterprise:
        System.out.println("\n========================================");
        System.out.println("-- TEST DE LA CLASE EMPRESA --");
        // Nueva instancia de clase Enterprise: Se crea un objeto llamado Emp
        Enterprise Emp = new Enterprise("Cafe", "Calle 98c #06-7", "3015900465", "1234");

        // Se imprimen los datos iniciales del objeto Emp
        System.out.println("----------------------------------------\n");
        System.out.println("*** Llamado al metodo toString() de la clase Enterprise con los datos iniciales");
        System.out.println(Emp);

        // Prueba de los getters
        System.out.println("----------------------------------------\n");
        System.out.println("*** Prueba de Getters");
        // Lectura del nombre
        System.out.println("Nombre de la empresa: " + Emp.getNombre());
        // Lectura de la direccion
        System.out.println("Direccion de la empresa: " + Emp.getDireccion());
        // Lectura del telefono
        System.out.println("Telefono de la empresa: " + Emp.getTelefono());
        // Lectura del NIT
        System.out.println("NIT de la empresa: " + Emp.getNit());

        //Prueba de los setters:
        System.out.println("----------------------------------------\n");
        System.out.println("*** Prueba de Setters");
        // Modificacion del nombre
        Emp.setNombre("Panadería");
        System.out.println("Se cambió el nombre...");
        // Modificacion de la direccion
        Emp.setDireccion("CAlle98_34-02");
        System.out.println("Se cambió la dirección...");
        // Modificacion del telefono
        Emp.setTelefono("30259066");
        System.out.println("Se cambió el telefono...");
        // Modificacion del NIT
        Emp.setNit("222999");
        System.out.println("Se cambió el NIT...");
        // Se imprimen los datos modificados del objeto Emp
        System.out.println("\n*** Llamado al metodo toString() de la clase Enterprise con los datos modificados");
        System.out.println(Emp);
    }

    private static void testerEmpleado() {
        //Clase Employee:
        System.out.println("\n========================================");
        System.out.println("-- TEST DE LA CLASE EMPLEADO --");
        // Nueva instancia de clase Employee: Se crea un objeto llamado emp
        Employee emp = new Employee("Mario", "XXXX@gmail.com", new Enterprise("InnoveD Col","Cra 56 # 24-10","5555555","123456987"), "Almacenista");

        // Se imprimen los datos iniciales del objeto emp
        System.out.println("----------------------------------------\n");
        System.out.println("*** Llamado al metodo toString() de la clase Employee con los datos iniciales");
        System.out.println(emp);

        // Prueba de los getters
        System.out.println("----------------------------------------\n");
        System.out.println("*** Prueba de Getters");
        // Lectura del nombre
        System.out.println("Nombre del empleado: " + emp.getNombre());
        // Lectura del correo
        System.out.println("Correo del empleado: " + emp.getCorreo());
        // Lectura de la empresa contratante
        System.out.println("Enterprise contratante: " + emp.getEmpresaContratante().getNombre());
        // Lectura del rol
        System.out.println("Rol del empleado: " + emp.getRol());

        //Prueba de los setters:
        System.out.println("----------------------------------------\n");
        System.out.println("*** Prueba de Setters");
        // Modificacion del nombre
        emp.setNombre("Hellder");
        System.out.println("Se cambió el nombre...");
        // Modificacion del correo
        emp.setCorreo("cccc@gmail.com");
        System.out.println("Se cambió el correo...");
        // Modificacion de la empresa contratante
        emp.setEmpresaContratante(enterprisePpal);
        System.out.println("Se cambió la empresa contratante...");
        // Modificacion del rol
        emp.setRol("Contador");
        System.out.println("Se cambió el rol...");
        // Se imprimen los datos modificados del objeto emp
        System.out.println("\n*** Llamado al metodo toString() de la clase Employee con los datos modificados");
        System.out.println(emp);
    }

    private static void testerMovimientoDinero() {

        //Clase Transaction:
        System.out.println("\n========================================");
        System.out.println("-- TEST DE LA CLASE MOVIMIENTODINERO --");
        // Nueva instancia de clase  Transaction: Se crea un objeto llamado mov
        Transaction mov = new Transaction(20000, "Consignacion inicial", "consignacion", employeeAdmin);

        // Se imprimen los datos iniciales del objeto mov
        System.out.println("----------------------------------------\n");
        System.out.println("*** Llamado al metodo toString() de la clase Transaction con los datos iniciales");
        System.out.println(mov);

        // Prueba de los getters
        System.out.println("----------------------------------------\n");
        System.out.println("*** Prueba de Getters");
        // Lectura del monto
        System.out.println("Monto del movimiento: " + mov.getMonto());
        // Lectura del concepto
        System.out.println("Concepto del movimiento: " + mov.getConcepto());
        // Lectura del tipo de movimiento
        System.out.println("Tipo de movimiento: " + mov.getTipoMovimiento());
        // Lectura del usuario que realiza el movimiento
        System.out.println("Usuario: " + mov.getUsuario().getNombre());

        //Prueba de los setters:
        System.out.println("----------------------------------------\n");
        System.out.println("*** Prueba de Setters");
        // Modificacion del monto (valor negativo)
        mov.setMonto(-20000);
        System.out.println("Se cambió el monto (valor negativo)...");
        // Modificacion del concepto
        mov.setConcepto("Retiro de $20000");
        System.out.println("Se cambió el concepto...");
        // Modificacion del tipo de movimiento
        mov.setTipoMovimiento("retiro");
        System.out.println("Se cambió el tipo de movimiento...");
        // Modificacion del usuario que realiza el movimiento
        mov.setUsuario(employeeOper);
        System.out.println("Se cambió el usuario que realiza el movimiento...");
        // Se imprimen los datos modificados del objeto mov
        System.out.println("\n*** Llamado al metodo toString() de la clase Transaction con los datos modificados");
        System.out.println(mov + "\n");
        // Modificacion del monto (valor positivo)
        mov.setMonto(50000);
        System.out.println("Se cambió el monto (valor positivo)...");
        // Modificacion del concepto
        mov.setConcepto("Consignacion de $50000");
        System.out.println("Se cambió el concepto...");
        // Modificacion del tipo de movimiento
        mov.setTipoMovimiento("consignacion");
        System.out.println("Se cambió el tipo de movimiento...");
        // Se imprimen los datos modificados del objeto mov
        System.out.println("\n*** Llamado al metodo toString() de la clase Transaction con los datos modificados");
        System.out.println(mov);

    }
}
