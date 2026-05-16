package com.biblioteca.core;
 
import java.time.LocalDate;
import java.util.List;
 
import com.biblioteca.users.Lector;
 
public class Solcitud {
  private LocalDate fechaSolicitud;
  private Lector solicitante;
  private Libro libro;
 
  public Solcitud(LocalDate fechaSolicitud, Lector solicitante, Libro libro) {
    this.fechaSolicitud = fechaSolicitud;
    this.solicitante = solicitante;
    this.libro = libro;
  }
 
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
 
  public void print() {
    System.out.println("------");
    System.out.println("Libro:        " + libro.getTitulo());
    System.out.println("Solicitante:  " + solicitante.getNombre());
    System.out.println("Fecha:        " + fechaSolicitud);
    System.out.println("------\n");
  }
 
  public Lector getSolicitante() { return solicitante; }
  public Libro getLibro() { return libro; }
  public LocalDate getFechaSolicitud() { return fechaSolicitud; }
}