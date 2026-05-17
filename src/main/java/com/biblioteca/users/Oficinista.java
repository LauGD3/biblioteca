package com.biblioteca.users;

/**
 * Representa a un oficinista dentro del sistema.
 *
 * Al igual que {@link Bibliotecario}, extiende {@link Personal} sin agregar
 * atributos ni comportamiento propio. La distinción entre ambos roles existe
 * para diferenciar tipos de personal dentro del sistema.
 *
 * @author Santiago Jaramillo (Okami012)
 * @version 1.0
 * @since 2026
 */
public class Oficinista extends Personal {

  /**
   * Crea un nuevo oficinista con toda su información personal y laboral.
   *
   * @param id            ID único del oficinista
   * @param nombre        nombre completo
   * @param clave         clave de acceso al sistema
   * @param direccion     dirección de residencia
   * @param nTelefono     número de teléfono
   * @param salario       salario del oficinista
   * @param numeroOficina número de la oficina asignada
   */
  public Oficinista(int id, String nombre, String clave, String direccion, int nTelefono, double salario, int numeroOficina) {
    super(id, nombre, clave, direccion, nTelefono, salario, numeroOficina);
  }
}
