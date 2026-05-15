package com.biblioteca;

// Java imports
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// Clases imports
import com.biblioteca.users.Oficinista;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        List<Oficinista> listaOficinistas = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        int opt = pantallaInicio(scan);
        switch (opt) {
            case 3:
                int adminOpt = adminOpt(scan);

                break;

            default:
                break;
        }
    }

    public static int pantallaInicio(Scanner scan) {
        int opt;
        System.out.println("-----------------------------------------------------------");
        System.out.println("      Vienvenido al sistema de gestion de Bebliotecas      ");
        System.out.println("-----------------------------------------------------------\n");

        do {
            System.out.println("Elije una de las opciones:");
            System.out.println("1. Ingresa \n" + "2. Salir del sistema\n" + "3. Funciones administrativas\n");
            System.out.println("-----------------\n");

            System.out.print("Ingresa la opción: ");
            opt = Integer.parseInt(scan.nextLine());
            if (opt < 1 || opt > 3) {
                System.out.println("La opción no es valida");
            }

        } while (opt < 1 || opt > 3);

        return opt;
    }

    public static int adminOpt(Scanner scan) {
        int opt;
        System.out.println("-------------------------------------------------");
        System.out.println("      Vienvenido al portal de administrador      ");
        System.out.println("-------------------------------------------------\n");

        do {
            System.out.println("Elije una de las opciones:");
            System.out.println("1. Añadir Oficinista\n" +
                    "2. Añadir Bibliotecario\n" +
                    "3. Ver libros prestados\n"
                    + "4. Ver todos los libros\n" +
                    "5. Salir");
            System.out.println("-----------------\n");

            System.out.print("Ingresa la opción: ");
            opt = Integer.parseInt(scan.nextLine());

        } while (opt < 1 || opt > 5);

        return opt;
    }

    public static void funcionesAdmin(int adminOpt) {
        switch (adminOpt) {
            case 1:
                agregarOficinista();
                break;

            default:
                break;
        }

    }

    public static List<Oficinista> agregarOficinista(List<Oficinista> listaOficinistas, Scanner scan) {
        int id;
        String nombre;
        String clave;
        String direccion;
        Integer nTelefono = null;
        double salario;
        int numeroOficina;
        Oficinista oficinista;

        System.out.println("----------------------------");
        System.out.println("     Agregar oficinista     ");
        System.out.println("----------------------------\n");

        do {
            System.out.println("Ingresa el ID: ");
            id = Integer.parseInt(scan.nextLine());
            for (Oficinista oficinistaI : listaOficinistas) {
                if (oficinistaI.getId() == id) {
                    System.out.println("El id que ingresaste ya está en uso.");
                    id = -1;
                    break;
                }
            }
        } while (id < 0);

        do {
            System.out.println("Ingresa el nombre: ");
            nombre = scan.nextLine();

            if (nombre.trim().isEmpty()) {
                System.out.println("Debes ingresar un nombre.\n");
            }
        } while (nombre.trim().isEmpty());

        do {
            System.out.println("Ingresa la clave temporal");
            clave = scan.nextLine();

            if (clave.trim().isEmpty()) {
                System.out.println("Debes ingresar una clave.\n");
            }

        } while (clave.trim().isEmpty());

        do {
            System.out.println("Ingresa la direccion de la oficina");
            direccion = scan.nextLine();

            if (direccion.trim().isEmpty()) {
                System.out.println("Debes ingresar una direccion.\n");
            }

        } while (direccion.trim().isEmpty());

        do {
            System.out.println("Ingresa el número de teléfono");
            nTelefono = Integer.parseInt(scan.nextLine());
        } while (nTelefono < 9999999);

        do {
            System.out.println("Ingresa el salario");
            salario = Double.parseDouble(scan.nextLine());
        } while (salario < 2000000);

        do {
            System.out.println("Ingresa el número de oficina");
            numeroOficina = Integer.parseInt(scan.nextLine());
        } while (numeroOficina < 0);

        oficinista = new Oficinista(id, nombre, clave, direccion, nTelefono, salario, numeroOficina);

        listaOficinistas.add(oficinista);
        return listaOficinistas;
    }
}
