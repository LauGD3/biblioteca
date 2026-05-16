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
 
  // ─── MÉTODOS ESTÁTICOS ───────────────────────────────────────────────────────
 
  public static void listarLibrosPrestados(List<Libro> listLibros) {
    boolean hayPrestados = false;
    for (Libro libro : listLibros) {
      if (libro.isEstadoPrestamo()) {
        libro.print();
        hayPrestados = true;
      }
    }
    if (!hayPrestados) {
      System.out.println("No hay libros prestados en este momento.\n");
    }
  }
 
  public static void listarLibros(List<Libro> listLibros) {
    if (listLibros.isEmpty()) {
      System.out.println("No hay libros registrados.\n");
      return;
    }
    for (Libro libro : listLibros) {
      libro.print();
    }
  }
 
  // Pide los datos de un libro nuevo y lo agrega a la lista
  public static void agregarLibro(List<Libro> listLibros, Scanner scan) {
    System.out.println("----------------------------");
    System.out.println("       Agregar Libro         ");
    System.out.println("----------------------------\n");
 
    // ID único
    int id;
    do {
      System.out.print("Ingresa el ID del libro: ");
      id = Integer.parseInt(scan.nextLine());
      boolean existe = false;
      for (Libro l : listLibros) {
        if (l.getIdLibro() == id) {
          System.out.println("Ese ID ya está en uso.\n");
          existe = true;
          break;
        }
      }
      if (existe) id = -1;
    } while (id < 0);
 
    String titulo;
    do {
      System.out.print("Ingresa el título: ");
      titulo = scan.nextLine();
      if (titulo.trim().isEmpty()) System.out.println("El título no puede estar vacío.\n");
    } while (titulo.trim().isEmpty());
 
    String autor;
    do {
      System.out.print("Ingresa el autor: ");
      autor = scan.nextLine();
      if (autor.trim().isEmpty()) System.out.println("El autor no puede estar vacío.\n");
    } while (autor.trim().isEmpty());
 
    String categoria;
    do {
      System.out.print("Ingresa la categoría: ");
      categoria = scan.nextLine();
      if (categoria.trim().isEmpty()) System.out.println("La categoría no puede estar vacía.\n");
    } while (categoria.trim().isEmpty());
 
    listLibros.add(new Libro(id, titulo, autor, categoria));
    System.out.println("Libro agregado exitosamente.\n");
  }
 
  // Busca un libro por ID y permite editarlo
  public static void editarLibro(List<Libro> listLibros, Scanner scan) {
    if (listLibros.isEmpty()) {
      System.out.println("No hay libros registrados.\n");
      return;
    }
 
    System.out.println("----------------------------");
    System.out.println("        Editar Libro         ");
    System.out.println("----------------------------\n");
 
    System.out.print("Ingresa el ID del libro a editar: ");
    int id = Integer.parseInt(scan.nextLine());
 
    Libro libroEncontrado = null;
    for (Libro l : listLibros) {
      if (l.getIdLibro() == id) {
        libroEncontrado = l;
        break;
      }
    }
 
    if (libroEncontrado == null) {
      System.out.println("No se encontró un libro con ese ID.\n");
      return;
    }
 
    libroEncontrado.cambiarInformacion(scan);
  }
 
  // ─── MÉTODOS DE INSTANCIA ─────────────────────────────────────────────────────
 
  public void print() {
    String res = estadoPrestamo ? "Sí" : "No";
 
    System.out.println("------");
    System.out.println("ID: " + idLibro);
    System.out.println("Título: " + titulo);
    System.out.println("Autor: " + autor);
    System.out.println("Categoría: " + categoria);
    System.out.println("En préstamo: " + res);
    System.out.println("------\n");
  }
 
  public void cambiarInformacion(Scanner scan) {
    System.out.println("----- Editando información del libro: " + idLibro + " -----");
    System.out.println("(Presiona Enter para mantener la información actual)\n");
 
    System.out.println("Título actual: " + this.titulo);
    System.out.print("Nuevo título: ");
    String titulo = scan.nextLine();
    if (!titulo.trim().isEmpty()) setTitulo(titulo);
 
    System.out.println("Autor actual: " + this.autor);
    System.out.print("Nuevo autor: ");                  // <- bug 3 corregido
    String autor = scan.nextLine();
    if (!autor.trim().isEmpty()) setAutor(autor);
 
    System.out.println("Categoría actual: " + this.categoria);  // <- bug 3 corregido
    System.out.print("Nueva categoría: ");
    String categoria = scan.nextLine();
    if (!categoria.trim().isEmpty()) setCategoria(categoria);
 
    System.out.println("---- Datos actualizados correctamente ----");
    print();
  }
 
  public Solcitud generarSolicitud(Lector lector) {
    if (estadoPrestamo == true) {
      System.out.println("El libro está prestado, no se puede generar una solicitud\n");
      return null;
    }
 
    Solcitud solicitud = new Solcitud(java.time.LocalDate.now(), lector, this);
    this.estadoPrestamo = true;
 
    System.out.println("---- Solicitud generada exitosamente ----");
    solicitud.print();
    return solicitud;
  }
 
  public int getIdLibro() { return idLibro; }
  public void setIdLibro(int idLibro) { this.idLibro = idLibro; }
  public String getTitulo() { return titulo; }
  public void setTitulo(String titulo) { this.titulo = titulo; }
  public String getAutor() { return autor; }
  public void setAutor(String autor) { this.autor = autor; }
  public String getCategoria() { return categoria; }
  public void setCategoria(String categoria) { this.categoria = categoria; }
  public boolean isEstadoPrestamo() { return estadoPrestamo; }
  public void setEstadoPrestamo(boolean estadoPrestamo) { this.estadoPrestamo = estadoPrestamo; }
}
