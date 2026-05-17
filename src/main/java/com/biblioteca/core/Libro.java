package com.biblioteca.core;

// Imports de java 
import java.util.List;
import java.util.Scanner;
// Imports de clases personales 
import com.biblioteca.users.Lector;

/**
 * Clase que representa un libro en el sistema de biblioteca.
 *
 * Guarda la información básica del libro (id, título, autor, categoría)
 * y si está prestado o no. También tiene métodos estáticos para listar,
 * agregar y editar libros desde consola.
 *
 * @author Santiago Jaramillo (Okami012)
 * @version 1.0
 * @since 2026
 */

public class Libro {
  private int idLibro;
  private String titulo;
  private String autor;
  private String categoria;
  private boolean estadoPrestamo;

  // ─── CONSTRUCTORES
  // ────────────────────────────────────────────────────────────

  /**
   * Crea un libro nuevo, disponible por defecto.
   *
   * @param idLibro   ID único del libro
   * @param titulo    título del libro
   * @param autor     autor del libro
   * @param categoria categoría del libro
   */

  public Libro(int idLibro, String titulo, String autor, String categoria) {
    this.idLibro = idLibro;
    this.titulo = titulo;
    this.autor = autor;
    this.categoria = categoria;
    this.estadoPrestamo = false;
  }

  /**
   * Crea un libro con un estado de préstamo específico.
   * Útil si se está cargando un libro que ya estaba prestado.
   *
   * @param idLibro        ID único del libro
   * @param titulo         título del libro
   * @param autor          autor del libro
   * @param categoria      categoría del libro
   * @param estadoPrestamo true si ya está prestado, false si está disponible
   */

  public Libro(int idLibro, String titulo, String autor, String categoria, boolean estadoPrestamo) {
    this.idLibro = idLibro;
    this.titulo = titulo;
    this.autor = autor;
    this.categoria = categoria;
    this.estadoPrestamo = estadoPrestamo;
  }

  // ─── MÉTODOS ESTÁTICOS ───────────────────────────────────────────────────────

  /**
   * Muestra en consola todos los libros que están prestados.
   * Si no hay ninguno, avisa al usuario.
   *
   * @param listLibros lista de libros donde buscar
   */

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

  /**
   * Muestra en consola todos los libros registrados.
   * Si la lista está vacía, avisa al usuario.
   *
   * @param listLibros lista de libros a mostrar
   */

  public static void listarLibros(List<Libro> listLibros) {
    if (listLibros.isEmpty()) {
      System.out.println("No hay libros registrados.\n");
      return;
    }
    for (Libro libro : listLibros) {
      libro.print();
    }
  }

  /**
   * Pide los datos de un libro nuevo por consola y lo agrega a la lista.
   * Valida que el ID no esté repetido y que los campos no estén vacíos.
   *
   * @param listLibros lista donde se agrega el libro nuevo
   * @param scan       scanner para leer la entrada del usuario
   */

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
      if (existe)
        id = -1;
    } while (id < 0);

    String titulo;
    do {
      System.out.print("Ingresa el título: ");
      titulo = scan.nextLine();
      if (titulo.trim().isEmpty())
        System.out.println("El título no puede estar vacío.\n");
    } while (titulo.trim().isEmpty());

    String autor;
    do {
      System.out.print("Ingresa el autor: ");
      autor = scan.nextLine();
      if (autor.trim().isEmpty())
        System.out.println("El autor no puede estar vacío.\n");
    } while (autor.trim().isEmpty());

    String categoria;
    do {
      System.out.print("Ingresa la categoría: ");
      categoria = scan.nextLine();
      if (categoria.trim().isEmpty())
        System.out.println("La categoría no puede estar vacía.\n");
    } while (categoria.trim().isEmpty());

    listLibros.add(new Libro(id, titulo, autor, categoria));
    System.out.println("Libro agregado exitosamente.\n");
  }

  /**
   * Busca un libro por ID y permite editarlo desde consola.
   * Si no encuentra el ID, avisa y no hace nada.
   *
   * @param listLibros lista donde buscar el libro
   * @param scan       scanner para leer la entrada del usuario
   */

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

  // ─── MÉTODOS DE INSTANCIA
  // ─────────────────────────────────────────────────────

  /**
   * Imprime en consola la información de este libro.
   */

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

  /**
   * Permite editar el título, autor y categoría del libro desde consola.
   * Si el usuario deja un campo vacío, se conserva el valor actual.
   *
   * @param scan scanner para leer la entrada del usuario
   */

  public void cambiarInformacion(Scanner scan) {
    System.out.println("----- Editando información del libro: " + idLibro + " -----");
    System.out.println("(Presiona Enter para mantener la información actual)\n");

    System.out.println("Título actual: " + this.titulo);
    System.out.print("Nuevo título: ");
    String titulo = scan.nextLine();
    if (!titulo.trim().isEmpty())
      setTitulo(titulo);

    System.out.println("Autor actual: " + this.autor);
    System.out.print("Nuevo autor: ");
    String autor = scan.nextLine();
    if (!autor.trim().isEmpty())
      setAutor(autor);

    System.out.println("Categoría actual: " + this.categoria);
    System.out.print("Nueva categoría: ");
    String categoria = scan.nextLine();
    if (!categoria.trim().isEmpty())
      setCategoria(categoria);

    System.out.println("---- Datos actualizados correctamente ----");
    print();
  }

  /**
   * Genera una solicitud de préstamo para el lector dado.
   * Si el libro ya está prestado, avisa y retorna null.
   *
   * @param lector el lector que quiere pedir prestado el libro
   * @return la solicitud creada, o null si el libro no está disponible
   */

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

  // ─── GETTERS Y SETTERS
  // ────────────────────────────────────────────────────────

  /** @return el ID del libro */
  public int getIdLibro() {
    return idLibro;
  }

  /** @param idLibro nuevo ID del libro */
  public void setIdLibro(int idLibro) {
    this.idLibro = idLibro;
  }

  /** @return el título del libro */
  public String getTitulo() {
    return titulo;
  }

  /** @param titulo nuevo título */
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  /** @return el autor del libro */
  public String getAutor() {
    return autor;
  }

  /** @param autor nuevo autor */
  public void setAutor(String autor) {
    this.autor = autor;
  }

  /** @return la categoría del libro */
  public String getCategoria() {
    return categoria;
  }

  /** @param categoria nueva categoría */
  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  /** @return true si está prestado, false si está disponible */
  public boolean isEstadoPrestamo() {
    return estadoPrestamo;
  }

  /**
   * @param estadoPrestamo true para marcar como prestado, false para disponible
   */
  public void setEstadoPrestamo(boolean estadoPrestamo) {
    this.estadoPrestamo = estadoPrestamo;
  }
}
