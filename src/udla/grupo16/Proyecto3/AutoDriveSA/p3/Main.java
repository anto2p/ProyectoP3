package udla.grupo16.Proyecto3.AutoDriveSA.p3;

import java.sql.Connection;
import java.util.Scanner;

/**
 * Punto de entrada del sistema y control de ciclo de vida.
 */
public class Main {

    /**
     * Método principal de ejecución.
     */
    public static void main(String[] args) {
        /** Inicialización de recursos de conexión y lectura */
        Utilidades util = new Utilidades();
        Connection conn = util.getConnection();
        Scanner sc = new Scanner(System.in);

        /** Instanciación de controladores modulares */
        GestionVehiculo gVehiculo = new GestionVehiculo();
        GestionVendedor gVendedor = new GestionVendedor();
        GestionVenta gVenta = new GestionVenta();
        Gestionable gReporte = new GestionReporte();

        /** Validación de conexión a base de datos */
        if (conn == null) return;

        int opcionPrincipal = 0;

        /** Menú interactivo principal */
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
                /** Procesamiento de selección de usuario */
                opcionPrincipal = Integer.parseInt(sc.nextLine());

                switch (opcionPrincipal) {
                    case 1:
                        /** Módulo de Vehículos */
                        gVehiculo.ejecutarModulo(sc, util, conn);
                        break;
                    case 2:
                        /** Módulo de Vendedores */
                        gVendedor.ejecutarModulo(sc, util, conn);
                        break;
                    case 3:
                        /** Módulo de Ventas */
                        gVenta.ejecutarModulo(sc, util, conn);
                        break;
                    case 4:
                        /** Módulo de Reportes */
                        gReporte.ejecutarModulo(sc, util, conn);
                        break;
                    case 5:
                        System.out.println("Cerrando sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                /** Control de excepciones y errores de entrada */
                System.out.println(">>> ERROR: " + e.getMessage());
            }
        } while (opcionPrincipal != 5);

        /** Cierre de recursos */
        sc.close();
    }
}