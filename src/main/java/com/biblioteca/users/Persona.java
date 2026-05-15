package com.biblioteca.users;

public abstract class Persona {
  protected int id;
  protected String nombre;
  protected String clave;
  protected String direccion;
  protected int nTelefono;

  public Persona(int id, String nombre, String clave, String direccion,  int nTelefono) {
    this.id = id;
    this.nombre = nombre;
    this.clave = clave;
    this.direccion = direccion;
    this.nTelefono = nTelefono;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getClave() {
    return clave;
  }

  public void setClave(String clave) {
    this.clave = clave;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public int getnTelefono() {
    return nTelefono;
  }

  public void setnTelefono(int nTelefono) {
    this.nTelefono = nTelefono;
  }

}
