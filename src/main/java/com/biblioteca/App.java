package com.biblioteca;

// Java imports
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biblioteca.core.Libro;
// Clases imports
import com.biblioteca.users.Personal;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        List<Personal> listaStaff = new ArrayList<>();
        List<Libro> listaLibros = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        int optInicio;
        do {
            optInicio = pantallaInicio(scan);

            if (optInicio == 3) {
                int optAdmin;
                do {
                    optAdmin = adminOpt(scan, listaStaff);
                    funcionesAdmin(optAdmin, scan, listaStaff);

                } while (optAdmin != 6);
            }
        } while (optInicio != 2);
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

    public static int adminOpt(Scanner scan, List<Personal> listaOficinistas) {
        int opt;
        System.out.println("-------------------------------------------------");
        System.out.println("      Vienvenido al portal de administrador      ");
        System.out.println("-------------------------------------------------\n");

        do {
            System.out.println("Elije una de las opciones:");
            System.out.println("1. Añadir Oficinista\n" +
                    "2. Añadir Bibliotecario\n" +
                    "3. Ver Personal\n" +  
                    "4. Ver libros prestados\n" +
                    "5. Ver todos los libros\n" +
                    "6. Salir");
            System.out.println("-----------------\n");

            System.out.print("Ingresa la opción: ");
            opt = Integer.parseInt(scan.nextLine());

        } while (opt < 1 || opt > 6);

        return opt;
    }

    public static void funcionesAdmin(int optSeleccionada, Scanner scan, List<Personal> listaPersonal) {
        switch (optSeleccionada) {
            case 1:
                Personal.registrarMiembroStaff(listaPersonal, scan, optSeleccionada);
                break;
            case 2:
                Personal.registrarMiembroStaff(listaPersonal, scan, optSeleccionada);
                break;
            case 3:
                Personal.listarPersonal(listaPersonal);
                break;
            case 5:
                System.out.println("Saliendo al menú principal...");
                break;
            default:
                System.out.println("Opción no implementada aún.");
                break;
        }
    }
}
