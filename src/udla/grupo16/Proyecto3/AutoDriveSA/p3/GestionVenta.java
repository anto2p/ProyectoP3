package udla.grupo16.Proyecto3.AutoDriveSA.p3;

import java.sql.Connection;
import java.util.Scanner;

/**
 * Control lógico del proceso de ventas y facturación.
 */
public class GestionVenta implements Gestionable {

    /**
     * Ejecución del menú de gestión de ventas.
     */
    @Override
    public void ejecutarModulo(Scanner lector, Utilidades util, Connection conexion) {
        int opcion = 0;
        do {
            System.out.println("\n--- GESTIÓN DE VENTAS ---");
            System.out.println("[1] Realizar una Venta");
            System.out.println("[2] Ver Historial de Ventas");
            System.out.println("[3] Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            try {
                /** Procesamiento de la opción seleccionada */
                opcion = Integer.parseInt(lector.nextLine());
                switch (opcion) {
                    case 1:
                        /** Inicio del proceso de facturación */
                        procesarVenta(lector, util, conexion);
                        pausar(lector);
                        break;
                    case 2:
                        /** Consulta del registro histórico de ventas */
                        util.obtenerVentas(conexion);
                        pausar(lector);
                        break;
                    case 3:
                        System.out.println(">>> Regresando al menú principal...");
                        break;
                    default:
                        System.out.println(">>> Opción no válida.");
                }
            } catch (Exception e) {
                /** Gestión de errores durante la operación */
                System.out.println(">>> ERROR: " + e.getMessage());
                pausar(lector);
            }
        } while (opcion != 3);
    }

    /**
     * Lógica para el registro de una nueva transacción.
     */
    private void procesarVenta(Scanner lector, Utilidades util, Connection conexion) {
        /** Selección de vehículo y vendedor */
        util.mostrarInventarioPorEstado(Estado_Vehiculo.Disponible, conexion);
        System.out.print("\nPlaca del vehículo: ");
        String placa = lector.nextLine();
        util.obtenerVendedores(conexion);
        System.out.print("ID del Vendedor: ");
        int idVendedor = Integer.parseInt(lector.nextLine());
        System.out.print("Monto Final ($): ");
        double montoPactado = Double.parseDouble(lector.nextLine());

        /** Validación de existencia y disponibilidad de datos */
        Vehiculo auto = util.buscarVehiculo(placa, conexion);
        Vendedor vend = util.buscarVendedorPorID(idVendedor, conexion);

        if (auto == null || vend == null || auto.getEstado() != Estado_Vehiculo.Disponible) {
            System.out.println(">>> ERROR: Datos inválidos o vehículo no disponible.");
            return;
        }

        /** Confirmación y persistencia de la venta */
        System.out.print("\n¿Confirmar venta de " + auto.getMarca() + " por $" + montoPactado + "? (S/N): ");
        if (lector.nextLine().equalsIgnoreCase("s")) {
            Venta v = new Venta(0, auto, vend, montoPactado);
            util.insertarVenta(v, conexion);
            /** Actualización del estado del vehículo en la base de datos */
            util.actualizarVehiculo(auto.getPlaca(), auto.getPrecio(), Estado_Vehiculo.Vendido, conexion);
            System.out.println(">>> ¡VENTA REGISTRADA CON ÉXITO!");
        }
    }
}