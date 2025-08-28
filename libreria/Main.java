public class Libreria {
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
