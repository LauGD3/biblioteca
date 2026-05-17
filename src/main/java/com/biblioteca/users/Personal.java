package com.biblioteca.users;

import java.util.List;
import java.util.Scanner;

/**
 * Clase abstracta que representa a un miembro del personal de la biblioteca.
 *
 * Extiende {@link Persona} agregando salario y número de oficina.
 * Es la clase padre de {@link Bibliotecario} y {@link Oficinista}.
 * No se puede instanciar directamente.
 *
 * @author Cristian Martínez (LauGD)
 * @version 1.0
 * @since 2026
 */
public abstract class Personal extends Persona {

  /** Salario del miembro del personal. */
  protected double salario;

  /** Número de la oficina asignada. */
  private int numeroOficina;

  // ─── CONSTRUCTOR
  // ──────────────────────────────────────────────────────────────

  /**
   * Crea un miembro del personal con toda su información.
   *
   * @param id            ID único
   * @param nombre        nombre completo
   * @param clave         clave de acceso al sistema
   * @param direccion     dirección de residencia
   * @param nTelefono     número de teléfono
   * @param salario       salario (mínimo 1.500.000)
   * @param numeroOficina número de la oficina asignada
   */
  public Personal(int id, String nombre, String clave, String direccion, int nTelefono, double salario,
      int numeroOficina) {
    super(id, nombre, clave, direccion, nTelefono);
    this.salario = salario;
    this.numeroOficina = numeroOficina;
  }

  // ─── MÉTODOS ESTÁTICOS
  // ────────────────────────────────────────────────────────

  /**
   * Muestra en consola la lista de todo el personal registrado.
   *
   * @param lista lista de personal a mostrar
   */
  public static void listarPersonal(List<Personal> lista) {
    System.out.println("--------------------------------");
    System.out.println("      Listado del Personal      ");
    System.out.println("--------------------------------\n");

    for (Personal personal : lista) {
      System.out.println("--------------------------------");
      System.out.println("ID: " + personal.getId() + "\n" +
          "Nombre: " + personal.getNombre() + "\n" +
          "Dirección: " + personal.getDireccion() + "\n" +
          "Número de teléfono: " + personal.getnTelefono() + "\n");
      System.out.println("--------------------------------");
    }
  }

  /**
   * Pide los datos de un nuevo miembro del personal por consola y lo agrega a la
   * lista.
   * Según el tipo, crea un {@link Oficinista} o un {@link Bibliotecario}.
   *
   * Validaciones:
   * - El ID no puede estar repetido.
   * - Nombre, clave y dirección no pueden estar vacíos.
   * - El salario mínimo es 1.500.000.
   * - El número de oficina debe ser mayor a 0.
   *
   * @param lista lista donde se agrega el nuevo miembro
   * @param scan  scanner para leer la entrada del usuario
   * @param tipo  1 para Oficinista, cualquier otro valor para Bibliotecario
   */
  public static void registrarMiembroStaff(List<Personal> lista, Scanner scan, int tipo) {
    int id;
    String nombre;
    String clave;
    String direccion;
    Integer nTelefono = null;
    double salario;
    int numeroOficina;

    System.out.println("----------------------------");
    System.out.println("     Agregar " + (tipo == 1 ? "Oficinista" : "Bibliotecario") + "     ");
    System.out.println("----------------------------\n");

    do {
      System.out.print("Ingresa el ID: ");
      id = Integer.parseInt(scan.nextLine());
      for (Personal personal : lista) {
        if (personal.getId() == id) {
          System.out.println("El id que ingresaste ya está en uso.");
          id = -1;
          break;
        }
      }
    } while (id < 0);

    do {
      System.out.print("Ingresa el nombre: ");
      nombre = scan.nextLine();

      if (nombre.trim().isEmpty()) {
        System.out.print("Debes ingresar un nombre.\n");
      }
    } while (nombre.trim().isEmpty());

    do {
      System.out.print("Ingresa la clave temporal: ");
      clave = scan.nextLine();

      if (clave.trim().isEmpty()) {
        System.out.println("Debes ingresar una clave.\n");
      }

    } while (clave.trim().isEmpty());

    do {
      System.out.print("Ingresa la direccion de la oficina: ");
      direccion = scan.nextLine();

      if (direccion.trim().isEmpty()) {
        System.out.print("Debes ingresar una direccion.\n");
      }

    } while (direccion.trim().isEmpty());

    do {
      System.out.print("Ingresa el número de teléfono: ");
      nTelefono = Integer.parseInt(scan.nextLine());
    } while (nTelefono < 1);

    do {
      System.out.print("Ingresa el salario: ");
      salario = Double.parseDouble(scan.nextLine());

      if (salario < 1500000) {
        System.out.print("El salario es demasiado bajo.");
      }
    } while (salario < 1500000);

    do {
      System.out.print("Ingresa el número de oficina: ");
      numeroOficina = Integer.parseInt(scan.nextLine());

      if (numeroOficina < 0) {
        System.out.print("Ingresa un número de oficina real");
      }

    } while (numeroOficina < 0);

    if (tipo == 1) {
      lista.add(new Oficinista(id, nombre, clave, direccion, nTelefono, salario, numeroOficina));
      System.out.println("Oficinista registrado.");
    } else {
      lista.add(new Bibliotecario(id, nombre, clave, direccion, nTelefono, salario, numeroOficina));
      System.out.println("Bibliotecario registrado.");
    }
  }

  // ─── MÉTODOS DE INSTANCIA
  // ─────────────────────────────────────────────────────

  /**
   * Imprime en consola el nombre, teléfono y número de oficina del miembro del
   * personal.
   */
  public void printInfo() {
    System.out.println("Nombre: " + nombre + "\n" +
        "Número de telefono: " + nTelefono + "\n" +
        "Número de oficina : " + numeroOficina);
  }

  // ─── GETTERS Y SETTERS
  // ────────────────────────────────────────────────────────

  /** @return el salario del miembro del personal */
  public double getSalario() {
    return salario;
  }

  /** @param salario nuevo salario */
  public void setSalario(double salario) {
    this.salario = salario;
  }

  /** @return el número de oficina asignada */
  public int getNumeroOficina() {
    return numeroOficina;
  }

  /** @param numeroOficina nuevo número de oficina */
  public void setNumeroOficina(int numeroOficina) {
    this.numeroOficina = numeroOficina;
  }
}