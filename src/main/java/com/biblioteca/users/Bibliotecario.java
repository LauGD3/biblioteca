package com.biblioteca.users;

public class Bibliotecario extends Personal {
  private int numeroOficina;

  public Bibliotecario(int id, String nombre, String clave, String direccion, int nTelefono, double salario,
      int numeroOficina) {
    super(id, nombre, clave, direccion, nTelefono, salario, numeroOficina);
  }

  @Override
  public void printInfo() {
    super.printInfo();
    System.out.println("Número de oficina: " + numeroOficina);
  }
}
