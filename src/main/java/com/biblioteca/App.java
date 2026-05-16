package com.biblioteca;

// Java imports
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biblioteca.core.Libro;
import com.biblioteca.users.Bibliotecario;
import com.biblioteca.users.Oficinista;
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

        listaStaff.add(new Oficinista(101, "Carlos Mendoza", "admin123", "Calle 45 #12-30", 3124567, 2500000.0, 1));
        listaStaff.add(new Oficinista(102, "Ana María Gómez", "oficina2026", "Carrera 23 #8-15", 3009876, 2600000.0, 2));

        listaStaff.add(new Bibliotecario(201, "Laura Restrepo", "biblio77", "Avenida Santander #45-12", 3154433,
                2800000.0, 10));
        listaStaff.add(new Bibliotecario(202, "Jorge Eliecer", "jorgeNtech", "Barrio El Cable", 3217766, 2900000.0, 11));

        listaLibros.add(new Libro(1, "Cien Años de Soledad", "Gabriel García Márquez", "Realismo Mágico", false));
        listaLibros.add(new Libro(2, "El Quijote de la Mancha", "Miguel de Cervantes", "Clásicos", true)); // Prestado
        listaLibros.add(new Libro(3, "Cálculo Variable", "James Stewart", "Matemáticas", false));
        listaLibros.add(new Libro(4, "Introducción a Java", "Deitel & Deitel", "Programación", true)); // Prestado
        listaLibros.add(new Libro(5, "Álgebra Lineal", "Gilbert Strang", "Matemáticas", false));
        Scanner scan = new Scanner(System.in);

        int optInicio;
        do {
            optInicio = pantallaInicio(scan);

            if (optInicio == 3) {
                int optAdmin;
                do {
                    optAdmin = adminOpt(scan);
                    funcionesAdmin(optAdmin, scan, listaStaff, listaLibros);

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

    public static int adminOpt(Scanner scan) {
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

    public static void funcionesAdmin(int optSeleccionada, Scanner scan, List<Personal> listaPersonal,
            List<Libro> listLibros) {
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
            case 4:
                Libro.listarLibrosPrestados(listLibros);
                break;
            case 5:
                Libro.listarLibros(listLibros);
                break;
            case 6:
                System.out.println("Saliendo del sistema");
                break;
            default:
                System.out.println("Opción no implementada aún.");
                break;
        }
    }
}
