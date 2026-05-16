package com.biblioteca.core;

import java.util.List;
import java.util.Scanner;

import com.biblioteca.users.Lector;

public class Libro {
  private int idLibro;
  private String titulo;
  private String autor;
  private String categoria;
  private boolean estadoPrestamo;

  public Libro(int idLibro, String titulo, String autor, String categoria) {
    this.idLibro = idLibro;
    this.titulo = titulo;
    this.autor = autor;
    this.categoria = categoria;
    this.estadoPrestamo = false;
  }

  public Libro(int idLibro, String titulo, String autor, String categoria, boolean estadoPrestamo) {
    this.idLibro = idLibro;
    this.titulo = titulo;
    this.autor = autor;
    this.categoria = categoria;
    this.estadoPrestamo = estadoPrestamo;
  }

  public static void listarLibrosPrestados(List<Libro> listLibros) {
    for (Libro libro : listLibros) {
      if (libro.isEstadoPrestamo()) {
        libro.print();
      }
    }
  }

  public static void listarLibros(List<Libro> listLibros) {
    for (Libro libro : listLibros) {
      libro.print();
    }
  }

  public void print() {
    String res = "";

    if (estadoPrestamo == true) {
      res = "Sí";
    } else {
      res = "No";
    }

    System.out.println("------\n");

    System.out.println("ID: " + idLibro + "\n" +
        "Título: " + titulo + "\n" +
        "Autor: " + autor + "\n" +
        "Categoría: " + categoria + "\n" +
        "En prestamo: " + res);

    System.out.println("------\n");
  }

  public void cambiarInformacion(Scanner scan) {
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

    System.out.println("---- Datos actualizados correctamente ----");
    print();
  }

  public void generarSolicitud(Lector lector) {
    if (estadoPrestamo == true) {
      System.out.println("El libro está prestado, no se puede generar una solicitud");
      return;
    }
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
