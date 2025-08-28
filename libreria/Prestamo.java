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