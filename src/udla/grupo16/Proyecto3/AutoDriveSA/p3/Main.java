package udla.grupo16.Proyecto3.AutoDriveSA.p3;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Utilidades util = new Utilidades();
        Connection conn = util.getConnection();
        Scanner sc = new Scanner(System.in);

        GestionVehiculo gVehiculo = new GestionVehiculo();
        GestionVendedor gVendedor = new GestionVendedor();
        GestionVenta gVenta = new GestionVenta();
        Gestionable gReporte = new GestionReporte();

        if (conn == null) return;

        int opcionPrincipal = 0;
        do {
            System.out.println("\n==========================================");
            System.out.println("   SISTEMA DE GESTIÓN AUTODRIVE S.A.  ");
            System.out.println("==========================================");
            System.out.println("1. GESTIÓN DE VEHÍCULOS");
            System.out.println("2. GESTIÓN DE VENDEDORES");
            System.out.println("3. REALIZAR UNA VENTA");
            System.out.println("4. REPORTE (RESUMEN)");
            System.out.println("5. SALIR");
            System.out.print("Seleccione una categoría: ");

            try {
                opcionPrincipal = Integer.parseInt(sc.nextLine());
                switch (opcionPrincipal) {
                    case 1:
                        gVehiculo.ejecutarModulo(sc, util, conn);
                        break;
                    case 2:
                        gVendedor.ejecutarModulo(sc, util, conn);
                        break;
                    case 3:
                        gVenta.ejecutarModulo(sc, util, conn);
                        break;
                    case 4:
                        gReporte.ejecutarModulo(sc, util, conn);
                        break;
                    case 5:
                        System.out.println("Cerrando sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println(">>> ERROR: " + e.getMessage());
            }
        } while (opcionPrincipal != 5);
        sc.close();
    }
}