package com.biblioteca.core;

import java.time.LocalDate;

import com.biblioteca.users.Lector;

public class Solcitud {
  private LocalDate fechaSolicitud;
  private Lector solicitante;

  public Solcitud(LocalDate fechaSolicitud, Lector solicitante) {
    this.fechaSolicitud = fechaSolicitud;
    this.solicitante = solicitante;
  }

  void print() {
    System.out.println("Solicitante: " + solicitante.getNombre() + "\n" + "Fecha: " + fechaSolicitud);
  }
}
