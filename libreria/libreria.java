import java.util.*;
import java.time.*;

class Libro {
    private String titulo;
    private String autor;
    private String codigo;
    private boolean disponible;

    public Libro(String titulo, String autor, String codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.disponible = true;
    }

    public String getCodigo() { return codigo; }
    public boolean isDisponible() { return disponible; }

    public void mostrarDatos() {
        System.out.println("[" + codigo + "] " + titulo + " - " + autor + 
                           (disponible ? " (Disponible)" : " (Prestado)"));
    }

    public void marcarPrestado() { disponible = false; }
    public void marcarDisponible() { disponible = true; }
}


class Usuario {
    private String nombre;
    private String idUsuario;
    private List<Libro> librosPrestados;

    public Usuario(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.librosPrestados = new ArrayList<>();
    }

    public String getIdUsuario() { return idUsuario; }
    public List<Libro> getLibrosPrestados() { return librosPrestados; }

    public void mostrarDatos() {
        System.out.println("Usuario: " + nombre + " (ID: " + idUsuario + ")");
        System.out.println("Libros prestados: ");
        for (Libro l : librosPrestados) {
            System.out.println(" - " + l.getCodigo());
        }
    }

    public boolean agregarPrestamo(Libro libro) {
        if (librosPrestados.size() < 3) {
            librosPrestados.add(libro);
            return true;
        }
        return false;
    }

    public boolean devolverLibro(Libro libro) {
        return librosPrestados.remove(libro);
    }
}


class Prestamo {
    Libro libro;
    Usuario usuario;
    LocalDate fechaInicio;
    LocalDate fechaLimite;

    public Prestamo(Libro libro, Usuario usuario) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaInicio = LocalDate.now();
        this.fechaLimite = fechaInicio.plusDays(7); 
    }

    public void mostrarDatos() {
        System.out.println("Libro: " + libro.getCodigo() + " | Usuario: " + usuario.getIdUsuario() +
                           " | Inicio: " + fechaInicio + " | Límite: " + fechaLimite);
    }
}


class Biblioteca {
    private List<Libro> libros;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;

    public Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    public void registrarLibro(String titulo, String autor, String codigo) {
        libros.add(new Libro(titulo, autor, codigo));
        System.out.println("Libro registrado con éxito.");
    }

    public void registrarUsuario(String nombre, String id) {
        usuarios.add(new Usuario(nombre, id));
        System.out.println("Usuario registrado con éxito.");
    }

    public void mostrarLibrosDisponibles() {
        for (Libro l : libros) {
            if (l.isDisponible()) l.mostrarDatos();
        }
    }

    public void mostrarUsuarios() {
        for (Usuario u : usuarios) {
            u.mostrarDatos();
        }
    }

    public void mostrarHistorialPrestamos() {
        for (Prestamo p : prestamos) {
            p.mostrarDatos();
        }
    }

    public void prestarLibro(String codigoLibro, String idUsuario) {
        Libro libro = buscarLibro(codigoLibro);
        Usuario usuario = buscarUsuario(idUsuario);

        if (libro == null || usuario == null) {
            System.out.println("Libro o usuario no encontrado.");
            return;
        }
        if (!libro.isDisponible()) {
            System.out.println("El libro no está disponible.");
            return;
        }
        if (!usuario.agregarPrestamo(libro)) {
            System.out.println("El usuario ya tiene 3 libros prestados.");
            return;
        }

        libro.marcarPrestado();
        Prestamo p = new Prestamo(libro, usuario);
        prestamos.add(p);
        System.out.println("Préstamo realizado con éxito.");
    }

    public void devolverLibro(String codigoLibro, String idUsuario) {
        Libro libro = buscarLibro(codigoLibro);
        Usuario usuario = buscarUsuario(idUsuario);

        if (libro == null || usuario == null) {
            System.out.println("Libro o usuario no encontrado.");
            return;
        }

        Prestamo prestamo = buscarPrestamo(libro, usuario);
        if (prestamo == null) {
            System.out.println("Ese préstamo no existe.");
            return;
        }

  
        usuario.devolverLibro(libro);
        libro.marcarDisponible();
        prestamos.remove(prestamo);

  
        LocalDate hoy = LocalDate.now();
        if (hoy.isAfter(prestamo.fechaLimite)) {
            long diasRetraso = Duration.between(prestamo.fechaLimite.atStartOfDay(), hoy.atStartOfDay()).toDays();
            long multa = diasRetraso * 500;
            System.out.println("Libro devuelto con retraso. Multa: $" + multa);
        } else {
            System.out.println("Libro devuelto sin multas.");
        }
    }

    private Libro buscarLibro(String codigo) {
        for (Libro l : libros) {
            if (l.getCodigo().equals(codigo)) return l;
        }
        return null;
    }

    private Usuario buscarUsuario(String id) {
        for (Usuario u : usuarios) {
            if (u.getIdUsuario().equals(id)) return u;
        }
        return null;
    }

    private Prestamo buscarPrestamo(Libro libro, Usuario usuario) {
        for (Prestamo p : prestamos) {
            if (p.libro == libro && p.usuario == usuario) return p;
        }
        return null;
    }
}


public class libreria {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblio = new Biblioteca();
        int opcion;

        do {
            System.out.println("\n--- MENU BIBLIOTECA ---");
            System.out.println("1. Registrar libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar libros disponibles");
            System.out.println("6. Mostrar usuarios");
            System.out.println("7. Mostrar historial de préstamos");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Código: ");
                    String codigo = sc.nextLine();
                    biblio.registrarLibro(titulo, autor, codigo);
                    break;
                case 2:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("ID Usuario: ");
                    String id = sc.nextLine();
                    biblio.registrarUsuario(nombre, id);
                    break;
                case 3:
                    System.out.print("Código libro: ");
                    String c1 = sc.nextLine();
                    System.out.print("ID Usuario: ");
                    String u1 = sc.nextLine();
                    biblio.prestarLibro(c1, u1);
                    break;
                case 4:
                    System.out.print("Código libro: ");
                    String c2 = sc.nextLine();
                    System.out.print("ID Usuario: ");
                    String u2 = sc.nextLine();
                    biblio.devolverLibro(c2, u2);
                    break;
                case 5:
                    biblio.mostrarLibrosDisponibles();
                    break;
                case 6:
                    biblio.mostrarUsuarios();
                    break;
                case 7:
                    biblio.mostrarHistorialPrestamos();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
