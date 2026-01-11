package udla.grupo16.Proyecto3.AutoDriveSA.p3;
import java.sql.Connection;
import java.util.Scanner;

public class GestionVendedor implements Gestionable {
    @Override
    public void ejecutarModulo(Scanner lector, Utilidades util, Connection conexion) {
        int opcion = 0;
        do {
            System.out.println("\n--- ADMINISTRACIÓN DE VENDEDORES ---");
            System.out.println("[1] Registrar Nuevo");
            System.out.println("[2] Eliminar por ID");
            System.out.println("[3] Ver Nómina Actual");
            System.out.println("[4] Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(lector.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.print("Cédula: ");  String ced = lector.nextLine();
                        System.out.print("Nombre: ");  String nom = lector.nextLine();
                        System.out.print("Años Exp: "); double exp = Double.parseDouble(lector.nextLine());
                        util.insertarVendedor(new Vendedor(ced, nom, exp), conexion);
                        pausar(lector);
                        break;
                    case 2:
                        System.out.print("Ingrese el ID del vendedor a eliminar: ");
                        int id = Integer.parseInt(lector.nextLine());
                        util.eliminarVendedor(id, conexion);
                        pausar(lector);
                        break;
                    case 3:
                        util.obtenerVendedores(conexion);
                        pausar(lector);
                        break;
                    case 4:
                        System.out.println(">>> Regresando al menú principal...");
                        break;
                    default:
                        System.out.println(">>> Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println(">>> ERROR: " + e.getMessage());
                pausar(lector);
            }
        } while (opcion != 4);
    }
}