package com.biblioteca.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biblioteca.core.Libro;
import com.biblioteca.core.Solcitud;

/**
 * Clase que representa a un lector registrado en el sistema de biblioteca.
 *
 * Extiende {@link Persona} y agrupa toda la lógica relacionada con los
 * lectores:
 * registro, listado, inicio de sesión y solicitud de préstamos.
 *
 * @author Santiago Jaramillo (Okami012)
 * @version 1.0
 * @since 2026
 */
public class Lector extends Persona {

  // ─── CONSTRUCTOR
  // ──────────────────────────────────────────────────────────────

  /**
   * Crea un nuevo lector con su información básica.
   *
   * @param id        ID único del lector
   * @param nombre    nombre completo
   * @param clave     clave de acceso al sistema
   * @param direccion dirección de residencia
   * @param nTelefono número de teléfono
   */
  public Lector(int id, String nombre, String clave, String direccion, int nTelefono) {
    super(id, nombre, clave, direccion, nTelefono);
  }

  // ─── MÉTODOS DE ADMINISTRACIÓN
  // ────────────────────────────────────────────────

  /**
   * Pide los datos de un nuevo lector por consola y lo agrega a la lista.
   * Valida que el ID no esté repetido y que los campos no estén vacíos.
   *
   * @param listaLectores lista donde se registra el nuevo lector
   * @param scan          scanner para leer la entrada del usuario
   */
  public static void registrarLector(List<Lector> listaLectores, Scanner scan) {
    int id;
    String nombre;
    String clave;
    String direccion;
    int nTelefono;

    System.out.println("----------------------------");
    System.out.println("      Registrar Lector       ");
    System.out.println("----------------------------\n");

    do {
      System.out.print("Ingresa el ID: ");
      id = Integer.parseInt(scan.nextLine());
      boolean existe = false;
      for (Lector l : listaLectores) {
        if (l.getId() == id) {
          System.out.println("Ese ID ya está en uso.\n");
          existe = true;
          break;
        }
      }
      if (existe)
        id = -1;
    } while (id < 0);

    do {
      System.out.print("Ingresa el nombre: ");
      nombre = scan.nextLine();
      if (nombre.trim().isEmpty())
        System.out.println("Debes ingresar un nombre.\n");
    } while (nombre.trim().isEmpty());

    do {
      System.out.print("Ingresa la clave: ");
      clave = scan.nextLine();
      if (clave.trim().isEmpty())
        System.out.println("Debes ingresar una clave.\n");
    } while (clave.trim().isEmpty());

    do {
      System.out.print("Ingresa la dirección: ");
      direccion = scan.nextLine();
      if (direccion.trim().isEmpty())
        System.out.println("Debes ingresar una dirección.\n");
    } while (direccion.trim().isEmpty());

    do {
      System.out.print("Ingresa el número de teléfono: ");
      nTelefono = Integer.parseInt(scan.nextLine());
      if (nTelefono < 1)
        System.out.println("Ingresa un teléfono válido.\n");
    } while (nTelefono < 1);

    listaLectores.add(new Lector(id, nombre, clave, direccion, nTelefono));
    System.out.println("Lector registrado exitosamente.\n");
  }

  /**
   * Muestra en consola la lista de todos los lectores registrados.
   * Si no hay ninguno, avisa al usuario.
   *
   * @param listaLectores lista de lectores a mostrar
   */
  public static void listarLectores(List<Lector> listaLectores) {
    if (listaLectores.isEmpty()) {
      System.out.println("No hay lectores registrados.\n");
      return;
    }
    System.out.println("--------------------------------");
    System.out.println("      Listado de Lectores       ");
    System.out.println("--------------------------------\n");
    for (Lector l : listaLectores) {
      System.out.println("ID: " + l.getId());
      System.out.println("Nombre: " + l.getNombre());
      System.out.println("Dirección: " + l.getDireccion());
      System.out.println("Teléfono: " + l.getnTelefono());
      System.out.println("--------------------------------");
    }
  }

  // ─── MÉTODOS DEL PORTAL DE LECTOR
  // ─────────────────────────────────────────────

  /**
   * Solicita ID y clave por consola y busca al lector correspondiente en la
   * lista.
   * Si las credenciales son correctas, retorna el lector. Si no, retorna null.
   *
   * @param listaLectores lista donde se busca al lector
   * @param scan          scanner para leer la entrada del usuario
   * @return el lector autenticado, o null si las credenciales no coinciden
   */
  public static Lector login(List<Lector> listaLectores, Scanner scan) {
    System.out.println("----------------------------------");
    System.out.println("        Acceso de Lector          ");
    System.out.println("----------------------------------\n");

    System.out.print("Ingresa tu ID: ");
    int idIngresado = Integer.parseInt(scan.nextLine());

    System.out.print("Ingresa tu clave: ");
    String claveIngresada = scan.nextLine();

    for (Lector lector : listaLectores) {
      if (lector.getId() == idIngresado && lector.getClave().equals(claveIngresada)) {
        System.out.println("\n¡Bienvenido, " + lector.getNombre() + "!\n");
        return lector;
      }
    }

    System.out.println("ID o clave incorrectos.\n");
    return null;
  }

  /**
   * Muestra el menú del portal del lector y retorna la opción elegida.
   * Repite la solicitud si la opción no es válida (1-3).
   *
   * @param scan         scanner para leer la entrada del usuario
   * @param nombreLector nombre del lector que está usando el portal
   * @return la opción seleccionada (1, 2 o 3)
   */
  public static int menuLector(Scanner scan, String nombreLector) {
    int opt;
    System.out.println("----------------------------------");
    System.out.println("  Portal de Lector: " + nombreLector);
    System.out.println("----------------------------------\n");

    do {
      System.out.println("Elige una opción:");
      System.out.println("1. Ver libros disponibles");
      System.out.println("2. Solicitar préstamo de un libro");
      System.out.println("3. Salir\n");

      System.out.print("Ingresa la opción: ");
      opt = Integer.parseInt(scan.nextLine());

      if (opt < 1 || opt > 3)
        System.out.println("Opción no válida.\n");
    } while (opt < 1 || opt > 3);

    return opt;
  }

  /**
   * Muestra los libros disponibles y permite al lector solicitar uno en préstamo.
   * Si no hay libros disponibles o el ID ingresado no existe, avisa al usuario.
   * La solicitud generada se agrega a la lista de solicitudes.
   *
   * @param lector           lector que realiza la solicitud
   * @param listaLibros      lista completa de libros del sistema
   * @param listaSolicitudes lista donde se registra la solicitud generada
   * @param scan             scanner para leer la entrada del usuario
   */
  public static void solicitarPrestamo(Lector lector, List<Libro> listaLibros,
      List<Solcitud> listaSolicitudes, Scanner scan) {
    List<Libro> disponibles = new ArrayList<>();
    for (Libro libro : listaLibros) {
      if (!libro.isEstadoPrestamo())
        disponibles.add(libro);
    }

    if (disponibles.isEmpty()) {
      System.out.println("No hay libros disponibles en este momento.\n");
      return;
    }

    System.out.println("------ Libros disponibles ------");
    for (Libro libro : disponibles)
      libro.print();

    System.out.print("Ingresa el ID del libro que deseas solicitar: ");
    int idLibro = Integer.parseInt(scan.nextLine());

    Libro libroElegido = null;
    for (Libro libro : disponibles) {
      if (libro.getIdLibro() == idLibro) {
        libroElegido = libro;
        break;
      }
    }

    if (libroElegido == null) {
      System.out.println("No se encontró un libro disponible con ese ID.\n");
      return;
    }

    Solcitud solicitud = libroElegido.generarSolicitud(lector);
    if (solicitud != null) {
      listaSolicitudes.add(solicitud);
    }
  }
}