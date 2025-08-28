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
