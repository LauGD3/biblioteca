package com.biblioteca.core;

import java.util.Scanner;

public class Libro {
  private int idLibro;
  private String titulo;
  private String autor;
  private String categoria;
  private boolean estadoPrestamo;

  public void print() {
    String res = "";

    if (estadoPrestamo == true) {
      res = "Sí";
    } else {
      res = "No";
    }

    System.out.println("ID: " + idLibro + "\n" +
        "Título: " + titulo + "\n" +
        "Autor: " + autor + "\n" +
        "Categoría: " + categoria + "\n" +
        "En prestamo: " + res);

  }

  public void cambiarInformacion() {
    Scanner scan = new Scanner(System.in);
    System.out.println("----- Editanto información del libro: " + idLibro + " -----");
    System.out.println("(Presiona Enter para mantner la información actual)");

    System.out.println("Título actual: " + this.titulo);
    System.out.print("Escribe el nuevo título: ");
    String titulo = scan.nextLine();
    if (!titulo.trim().isEmpty()) {
      setTitulo(titulo);
    }

    System.out.println("Autor actual: " + this.autor);
    System.out.print("Escribe el nuevo título: ");
    String autor = scan.nextLine();
    if (!autor.trim().isEmpty()) {
      setAutor(autor);
    }

    System.out.println("Autor actual: " + this.autor);
    System.out.print("Escribe la nueva categoría: ");
    String categoria = scan.nextLine();
    if (!categoria.trim().isEmpty()) {
      setCategoria(categoria);
    }

    Integer val = null;
    do {
      System.out.println("Estado actual de prestamo: " + (this.estadoPrestamo ? "Sí" : "No"));
      System.out.print("Escribe el nuevo estado (1. sí, 2. No): ");
      val = Integer.parseInt(scan.nextLine());

    } while ((val >= 1 && val <= 2) || val == null);

    scan.close();

  }

  public int getIdLibro() {
    return idLibro;
  }

  public void setIdLibro(int idLibro) {
    this.idLibro = idLibro;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public boolean isEstadoPrestamo() {
    return estadoPrestamo;
  }

  public void setEstadoPrestamo(boolean estadoPrestamo) {
    this.estadoPrestamo = estadoPrestamo;
  }
}
