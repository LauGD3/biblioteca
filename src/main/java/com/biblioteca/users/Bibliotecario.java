package com.biblioteca.users;

/**
 * Representa a un bibliotecario dentro del sistema.
 *
 * Extiende {@link Personal} sin agregar atributos ni comportamiento propio,
 * ya que toda la información del bibliotecario (nombre, clave, salario, etc.)
 * se maneja en la clase padre.
 *
 * @author Santiago Jaramillo (Okami012)
 * @version 1.0
 * @since 2026
 */
public class Bibliotecario extends Personal {

  /**
   * Crea un nuevo bibliotecario con toda su información personal y laboral.
   *
   * @param id            ID único del bibliotecario
   * @param nombre        nombre completo
   * @param clave         clave de acceso al sistema
   * @param direccion     dirección de residencia
   * @param nTelefono     número de teléfono
   * @param salario       salario del bibliotecario
   * @param numeroOficina número de la oficina asignada
   */
  public Bibliotecario(int id, String nombre, String clave, String direccion, int nTelefono, double salario,
      int numeroOficina) {
    super(id, nombre, clave, direccion, nTelefono, salario, numeroOficina);
  }
}
