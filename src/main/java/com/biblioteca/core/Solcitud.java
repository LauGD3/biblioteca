package com.biblioteca.core;

import java.time.LocalDate;
import java.util.List;

import com.biblioteca.users.Lector;

/**
 * Clase que representa una solicitud de préstamo en el sistema de biblioteca.
 *
 * Registra qué libro fue solicitado, quién lo pidió y en qué fecha.
 * Las solicitudes se crean desde {@link Libro#generarSolicitud(Lector)}.
 *
 * @author Santiago Jaramillo (Okami012)
 * @version 1.0
 * @since 2026
 */
public class Solcitud {

  /** Fecha en la que se generó la solicitud. */
  private LocalDate fechaSolicitud;

  /** Lector que solicitó el préstamo. */
  private Lector solicitante;

  /** Libro que fue solicitado. */
  private Libro libro;

  // ─── CONSTRUCTOR
  // ──────────────────────────────────────────────────────────────

  /**
   * Crea una nueva solicitud de préstamo.
   *
   * @param fechaSolicitud fecha en la que se realiza la solicitud
   * @param solicitante    lector que pide el libro
   * @param libro          libro que se está solicitando
   */
  public Solcitud(LocalDate fechaSolicitud, Lector solicitante, Libro libro) {
    this.fechaSolicitud = fechaSolicitud;
    this.solicitante = solicitante;
    this.libro = libro;
  }

  // ─── MÉTODOS ESTÁTICOS
  // ────────────────────────────────────────────────────────

  /**
   * Muestra en consola el historial completo de solicitudes.
   * Si no hay ninguna, avisa al usuario.
   *
   * @param listaSolicitudes lista de solicitudes a mostrar
   */
  public static void listarSolicitudes(List<Solcitud> listaSolicitudes) {
    if (listaSolicitudes.isEmpty()) {
      System.out.println("No hay solicitudes registradas.\n");
      return;
    }
    System.out.println("--------------------------------");
    System.out.println("    Historial de solicitudes    ");
    System.out.println("--------------------------------\n");
    for (Solcitud s : listaSolicitudes) {
      s.print();
    }
  }

  // ─── MÉTODOS DE INSTANCIA
  // ─────────────────────────────────────────────────────

  /**
   * Imprime en consola el resumen de esta solicitud:
   * título del libro, nombre del solicitante y fecha.
   */
  public void print() {
    System.out.println("------");
    System.out.println("Libro:        " + libro.getTitulo());
    System.out.println("Solicitante:  " + solicitante.getNombre());
    System.out.println("Fecha:        " + fechaSolicitud);
    System.out.println("------\n");
  }

  // ─── GETTERS
  // ──────────────────────────────────────────────────────────────────

  /** @return el lector que hizo la solicitud */
  public Lector getSolicitante() {
    return solicitante;
  }

  /** @return el libro solicitado */
  public Libro getLibro() {
    return libro;
  }

  /** @return la fecha en que se generó la solicitud */
  public LocalDate getFechaSolicitud() {
    return fechaSolicitud;
  }
}