package com.biblioteca.users;

public class Oficinista extends Personal {
  private int numeroOficina;

  public Oficinista(int id, String nombre, String clave, String direccion, int nTelefono, double salario, int numeroOficina) {
    super(id, nombre, clave, direccion, nTelefono, salario);
    this.numeroOficina = numeroOficina;

  }

  @Override
  public void printInfo() {
    super.printInfo();
    System.out.println("Número de oficina: " + numeroOficina);
  }
}
