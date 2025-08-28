import java.util.*;
import java.time.*;

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
    public void prestarLibro(Integer  codigolibro, Integer idUsuario) {
        Libr libro = buscarLibro (codigoLibro);
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
}

    