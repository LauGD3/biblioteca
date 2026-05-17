package com.biblioteca.users;

/**
 * Clase base abstracta para todos los usuarios del sistema.
 *
 * Define los atributos y comportamientos comunes a cualquier persona
 * registrada, ya sea un lector o un miembro del personal.
 * No se puede instanciar directamente.
 *
 * @author Cristian Martínez (LauGD3)
 * @version 1.0
 * @since 2026
 */
public abstract class Persona {

  /** ID único de la persona. */
  protected int id;

  /** Nombre completo de la persona. */
  protected String nombre;

  /** Clave de acceso al sistema. */
  protected String clave;

  /** Dirección de residencia. */
  protected String direccion;

  /** Número de teléfono de contacto. */
  protected int nTelefono;

  // ─── CONSTRUCTOR
  // ──────────────────────────────────────────────────────────────

  /**
   * Crea una persona con su información básica.
   *
   * @param id        ID único de la persona
   * @param nombre    nombre completo
   * @param clave     clave de acceso al sistema
   * @param direccion dirección de residencia
   * @param nTelefono número de teléfono
   */
  public Persona(int id, String nombre, String clave, String direccion, int nTelefono) {
    this.id = id;
    this.nombre = nombre;
    this.clave = clave;
    this.direccion = direccion;
    this.nTelefono = nTelefono;
  }

  // ─── GETTERS Y SETTERS
  // ────────────────────────────────────────────────────────

  /** @return el ID de la persona */
  public int getId() {
    return id;
  }

  /** @param id nuevo ID de la persona */
  public void setId(int id) {
    this.id = id;
  }

  /** @return el nombre de la persona */
  public String getNombre() {
    return nombre;
  }

  /** @param nombre nuevo nombre */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /** @return la clave de acceso */
  public String getClave() {
    return clave;
  }

  /** @param clave nueva clave de acceso */
  public void setClave(String clave) {
    this.clave = clave;
  }

  /** @return la dirección de residencia */
  public String getDireccion() {
    return direccion;
  }

  /** @param direccion nueva dirección */
  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  /** @return el número de teléfono */
  public int getnTelefono() {
    return nTelefono;
  }

  /** @param nTelefono nuevo número de teléfono */
  public void setnTelefono(int nTelefono) {
    this.nTelefono = nTelefono;
  }
}