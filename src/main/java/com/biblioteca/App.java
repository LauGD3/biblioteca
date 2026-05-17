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
 
public class App {
    public static void main(String[] args) {
        List<Personal> listaStaff = new ArrayList<>();
        List<Libro> listaLibros = new ArrayList<>();
        List<Lector> listaLectores = new ArrayList<>();
        List<Solcitud> listaSolicitudes = new ArrayList<>();
 
        // --- QUEMADO DE DATOS (DATA SEED) ---
 
        // 1. Oficinistas
        listaStaff.add(new Oficinista(101, "Carlos Mendoza", "admin123", "Calle 45 #12-30", 3124567, 2500000.0, 1));
        listaStaff.add(new Oficinista(102, "Ana María Gómez", "oficina2026", "Carrera 23 #8-15", 3009876, 2600000.0, 2));
 
        // 2. Bibliotecarios
        listaStaff.add(new Bibliotecario(201, "Laura Restrepo", "biblio77", "Avenida Santander #45-12", 3154433, 2800000.0, 10));
        listaStaff.add(new Bibliotecario(202, "Jorge Eliecer", "jorgeNtech", "Barrio El Cable", 3217766, 2900000.0, 11));
 
        // 3. Lectores
        listaLectores.add(new Lector(301, "Sofía Martínez", "sofia123", "Calle 10 #5-20", 3101234));
        listaLectores.add(new Lector(302, "Pedro Hernández", "pedro456", "Carrera 8 #3-15", 3209876));
 
        // 4. Libros
        listaLibros.add(new Libro(1, "Cien Años de Soledad", "Gabriel García Márquez", "Realismo Mágico", false));
        listaLibros.add(new Libro(2, "El Quijote de la Mancha", "Miguel de Cervantes", "Clásicos", true));
        listaLibros.add(new Libro(3, "Cálculo Variable", "James Stewart", "Matemáticas", false));
        listaLibros.add(new Libro(4, "Introducción a Java", "Deitel & Deitel", "Programación", true));
        listaLibros.add(new Libro(5, "Álgebra Lineal", "Gilbert Strang", "Matemáticas", false));
 
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
            if (opt < 1 || opt > 3) System.out.println("La opción no es valida");
 
        } while (opt < 1 || opt > 3);
 
        return opt;
    }
 
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
 
            if (opt < 1 || opt > 11) System.out.println("Opción no válida.\n");
        } while (opt < 1 || opt > 11);
 
        return opt;
    }
 
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
}
