package udla.grupo16.Proyecto3.AutoDriveSA.p3;

import java.sql.Connection;
import java.util.Scanner;

public class GestionReporte implements Gestionable {
    @Override
    public void ejecutarModulo(Scanner lector, Utilidades util, Connection conexion) {
        System.out.println("\n==========================================");
        System.out.println("                 REPORTES                 ");
        System.out.println("==========================================");

        System.out.println("\n--- INVENTARIO ---");
        util.obtenerVehiculos(conexion);

        System.out.println("\n--- PERSONAL ---");
        util.obtenerVendedores(conexion);

        System.out.println("\n--- HISTORIAL DE VENTAS ---");
        util.obtenerVentas(conexion);

        pausar(lector);
    }
}