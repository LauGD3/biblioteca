package com.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biblioteca.core.Libro;
import com.biblioteca.core.Solcitud;
import com.biblioteca.users.Bibliotecario;
import com.biblioteca.users.Lector;
import com.biblioteca.users.Oficinista;
import com.biblioteca.users.Personal;

/**
 * Clase principal del sistema de gestión de biblioteca.
 *
 * Contiene el punto de entrada del programa y los métodos que controlan
 * el flujo general: pantalla de inicio, portal del lector y panel
 * administrativo.
 *
 * @author Santiago Jaramillo (Okami012)
 * @author Cristian Martinez (LauGD3)
 * @version 1.0
 * @since 2026
 */
public class App {

    /**
     * Punto de entrada del programa.
     *
     * Inicializa las listas del sistema con datos de prueba y lanza el bucle
     * principal que mantiene el menú activo hasta que el usuario elige salir.
     *
     * @param args argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        List<Personal> listaStaff = new ArrayList<>();
        List<Libro> listaLibros = new ArrayList<>();
        List<Lector> listaLectores = new ArrayList<>();
        List<Solcitud> listaSolicitudes = new ArrayList<>();

        cargarStaff(listaStaff);
        cargarLectores(listaLectores);
        cargarLibros(listaLibros);

        Scanner scan = new Scanner(System.in);

        int optInicio;
        do {
            optInicio = pantallaInicio(scan);

            // --- Opción 1: Flujo del Lector ---
            if (optInicio == 1) {
                Lector lectorActivo = Lector.login(listaLectores, scan);

                if (lectorActivo != null) {
                    int optLector;
                    do {
                        optLector = Lector.menuLector(scan, lectorActivo.getNombre());

                        switch (optLector) {
                            case 1:
                                Libro.listarLibros(listaLibros);
                                break;
                            case 2:
                                Lector.solicitarPrestamo(lectorActivo, listaLibros, listaSolicitudes, scan);
                                break;
                            case 3:
                                System.out.println("Cerrando sesión...\n");
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                break;
                        }
                    } while (optLector != 3);
                }
            }

            // --- Opción 3: Flujo del Administrador ---
            if (optInicio == 3) {
                if (loginAdmin(scan)) {
                    int optAdmin;
                    do {
                        optAdmin = adminOpt(scan);
                        funcionesAdmin(optAdmin, scan, listaStaff, listaLibros, listaLectores, listaSolicitudes);
                    } while (optAdmin != 11);
                } else {
                    System.out.println("Acceso denegado. Volviendo al menú principal.\n");
                }
            }

        } while (optInicio != 2);
    }

    /**
     * Muestra la pantalla de inicio y retorna la opción elegida por el usuario.
     * Opciones: 1 para ingresar como lector, 2 para salir, 3 para administración.
     *
     * @param scan scanner para leer la entrada del usuario
     * @return la opción seleccionada (1, 2 o 3)
     */
    public static int pantallaInicio(Scanner scan) {
        int opt;
        System.out.println("-----------------------------------------------------------");
        System.out.println("      Bienvenido al sistema de gestion de Bebliotecas      ");
        System.out.println("-----------------------------------------------------------\n");

        do {
            System.out.println("Elije una de las opciones:");
            System.out.println("1. Ingresa\n" + "2. Salir del sistema\n" + "3. Funciones administrativas\n");
            System.out.println("-----------------\n");

            System.out.print("Ingresa la opción: ");
            opt = Integer.parseInt(scan.nextLine());
            if (opt < 1 || opt > 3)
                System.out.println("La opción no es valida");

        } while (opt < 1 || opt > 3);

        return opt;
    }

    /**
     * Muestra el menú del panel administrativo y retorna la opción elegida.
     * Las opciones van del 1 al 11, donde 11 es salir del panel.
     *
     * @param scan scanner para leer la entrada del usuario
     * @return la opción seleccionada (1 a 11)
     */
    public static int adminOpt(Scanner scan) {
        int opt;
        System.out.println("-------------------------------------------------");
        System.out.println("      Bienvenido al portal de administrador      ");
        System.out.println("-------------------------------------------------\n");

        do {
            System.out.println("Elije una de las opciones:");
            System.out.println(
                    "1.  Añadir Oficinista\n" +
                            "2.  Añadir Bibliotecario\n" +
                            "3.  Añadir Lector\n" +
                            "4.  Ver Personal\n" +
                            "5.  Ver Lectores\n" +
                            "6.  Ver libros prestados\n" +
                            "7.  Ver todos los libros\n" +
                            "8.  Agregar libro\n" +
                            "9.  Editar libro\n" +
                            "10. Ver historial de solicitudes\n" +
                            "11. Salir");
            System.out.println("-----------------\n");

            System.out.print("Ingresa la opción: ");
            opt = Integer.parseInt(scan.nextLine());

            if (opt < 1 || opt > 11)
                System.out.println("Opción no válida.\n");
        } while (opt < 1 || opt > 11);

        return opt;
    }

    /**
     * Solicita la clave de administrador y valida el acceso.
     * Permite un máximo de 3 intentos antes de denegar el acceso.
     *
     * @param scan scanner para leer la entrada del usuario
     * @return true si la clave es correcta, false si se agotan los intentos
     */
    public static boolean loginAdmin(Scanner scan) {
        final String CLAVE_ADMIN = "admin2026";
        final int MAX_INTENTOS = 3;

        System.out.println("----------------------------------");
        System.out.println("      Acceso Administrativo       ");
        System.out.println("----------------------------------\n");

        for (int intento = 1; intento <= MAX_INTENTOS; intento++) {
            System.out.print("Ingresa la clave de administrador: ");
            String claveIngresada = scan.nextLine();

            if (claveIngresada.equals(CLAVE_ADMIN)) {
                System.out.println("Acceso concedido.\n");
                return true;
            }

            int restantes = MAX_INTENTOS - intento;
            if (restantes > 0) {
                System.out.println("Clave incorrecta. Intentos restantes: " + restantes + "\n");
            }
        }

        System.out.println("Demasiados intentos fallidos.\n");
        return false;
    }

    /**
     * Ejecuta la función administrativa correspondiente a la opción seleccionada.
     * Actúa como despachador del panel admin, delegando cada acción a su clase
     * correspondiente.
     *
     * @param optSeleccionada  opción elegida en el menú administrativo (1 a 11)
     * @param scan             scanner para leer la entrada del usuario
     * @param listaPersonal    lista del personal del sistema
     * @param listLibros       lista de libros del sistema
     * @param listaLectores    lista de lectores registrados
     * @param listaSolicitudes lista de solicitudes de préstamo
     */
    public static void funcionesAdmin(int optSeleccionada, Scanner scan, List<Personal> listaPersonal,
            List<Libro> listLibros, List<Lector> listaLectores, List<Solcitud> listaSolicitudes) {
        switch (optSeleccionada) {
            case 1:
                Personal.registrarMiembroStaff(listaPersonal, scan, optSeleccionada);
                break;
            case 2:
                Personal.registrarMiembroStaff(listaPersonal, scan, optSeleccionada);
                break;
            case 3:
                Lector.registrarLector(listaLectores, scan);
                break;
            case 4:
                Personal.listarPersonal(listaPersonal);
                break;
            case 5:
                Lector.listarLectores(listaLectores);
                break;
            case 6:
                Libro.listarLibrosPrestados(listLibros);
                break;
            case 7:
                Libro.listarLibros(listLibros);
                break;
            case 8:
                Libro.agregarLibro(listLibros, scan);
                break;
            case 9:
                Libro.editarLibro(listLibros, scan);
                break;
            case 10:
                Solcitud.listarSolicitudes(listaSolicitudes);
                break;
            case 11:
                System.out.println("Saliendo del panel admin.\n");
                break;
            default:
                System.out.println("Opción no implementada aún.");
                break;
        }
    }
    // ─── DATA SEED
    // ────────────────────────────────────────────────────────────────

    /**
     * Carga el personal de prueba (oficinistas y bibliotecarios) en la lista.
     *
     * @param listaStaff lista donde se agregan los miembros del personal
     */
    public static void cargarStaff(List<Personal> listaStaff) {
        listaStaff.add(new Oficinista(101, "Carlos Mendoza", "admin123", "Calle 45 #12-30", 3124567, 2500000.0, 1));
        listaStaff
                .add(new Oficinista(102, "Ana María Gómez", "oficina2026", "Carrera 23 #8-15", 3009876, 2600000.0, 2));
        listaStaff.add(new Bibliotecario(201, "Laura Restrepo", "biblio77", "Avenida Santander #45-12", 3154433,
                2800000.0, 10));
        listaStaff
                .add(new Bibliotecario(202, "Jorge Eliecer", "jorgeNtech", "Barrio El Cable", 3217766, 2900000.0, 11));
    }

    /**
     * Carga los lectores de prueba en la lista.
     *
     * @param listaLectores lista donde se agregan los lectores
     */
    public static void cargarLectores(List<Lector> listaLectores) {
        listaLectores.add(new Lector(301, "Sofía Martínez", "sofia123", "Calle 10 #5-20", 3101234));
        listaLectores.add(new Lector(302, "Pedro Hernández", "pedro456", "Carrera 8 #3-15", 3209876));
    }

    /**
     * Carga los libros de prueba en la lista.
     * Algunos ya vienen marcados como prestados para simular el estado del sistema.
     *
     * @param listaLibros lista donde se agregan los libros
     */
    public static void cargarLibros(List<Libro> listaLibros) {
        listaLibros.add(new Libro(1, "Cien Años de Soledad", "Gabriel García Márquez", "Realismo Mágico", false));
        listaLibros.add(new Libro(2, "El Quijote de la Mancha", "Miguel de Cervantes", "Clásicos", true));
        listaLibros.add(new Libro(3, "Cálculo Variable", "James Stewart", "Matemáticas", false));
        listaLibros.add(new Libro(4, "Introducción a Java", "Deitel & Deitel", "Programación", true));
        listaLibros.add(new Libro(5, "Álgebra Lineal", "Gilbert Strang", "Matemáticas", false));
    }
}