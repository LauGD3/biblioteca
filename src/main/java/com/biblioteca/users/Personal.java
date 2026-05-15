package com.biblioteca.users;

public abstract class Personal extends Persona {
  protected double salario;

  public Personal(int id, String nombre, String clave, String direccion, int nTelefono, double salario) {
    super(id, nombre, clave, direccion, nTelefono);
    this.salario = salario;
  }

  public void printInfo() {
    System.out.println("Nombre: " + nombre + "\n" + "Apellido: " + direccion + "\n" + "Número de telefono: " + nTelefono);
  }
}
