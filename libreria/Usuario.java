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
