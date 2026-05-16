
package com.biblioteca.users;

import java.util.List;
import java.util.Scanner;

public abstract class Personal extends Persona {
  protected double salario;
  private int numeroOficina;

  public Personal(int id, String nombre, String clave, String direccion, int nTelefono, double salario,
      int numeroOficina) {
    super(id, nombre, clave, direccion, nTelefono);
    this.salario = salario;
    this.numeroOficina = numeroOficina;
  }

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
      // Creamos Oficinista pero se guarda en la lista de Personal
      lista.add(new Oficinista(id, nombre, clave, direccion, nTelefono, salario, numeroOficina));
      System.out.println("Oficinista registrado.");
    } else {
      // Creamos Bibliotecario
      lista.add(new Bibliotecario(id, nombre, clave, direccion, nTelefono, salario, numeroOficina));
      System.out.println("Bibliotecario registrado.");
    }
  }

  public void printInfo() {
    System.out
        .println("Nombre: " + nombre + "\n" +
            "Número de telefono: " + nTelefono + "\n" +
            "Número de oficina : " + numeroOficina);
  }
}
