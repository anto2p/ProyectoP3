package udla.grupo16.Proyecto3.AutoDriveSA.p3;

import java.sql.Connection;
import java.util.Scanner;

/**
 * Control lógico de operaciones sobre vehículos (CRUD).
 */
public class GestionVehiculo implements Gestionable {

    /**
     * Ejecución del submenú de gestión vehicular.
     */
    @Override
    public void ejecutarModulo(Scanner lector, Utilidades util, Connection conexion) {
        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ VEHÍCULOS ---");
            System.out.println("[1] Registrar Nuevo");
            System.out.println("[2] Editar Precio/Estado");
            System.out.println("[3] Eliminar de la Base");
            System.out.println("[4] Ver Listado de Vehículos");
            System.out.println("[5] Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            try {
                /** Procesamiento de la selección del usuario */
                opcion = Integer.parseInt(lector.nextLine());
                switch (opcion) {
                    case 1:
                        /** Registro de nuevo vehículo con validación de placa única */
                        System.out.print("Ingrese Placa: ");
                        String placa = lector.nextLine();
                        if (util.buscarVehiculo(placa, conexion) != null) {
                            System.out.println(">>> ERROR: El vehículo ya existe.");
                        } else {
                            System.out.print("Marca: ");  String marca = lector.nextLine();
                            System.out.print("Modelo: "); String modelo = lector.nextLine();
                            System.out.print("Precio: "); double precio = Double.parseDouble(lector.nextLine());
                            util.insertarVehiculo(new Vehiculo(placa, marca, modelo, precio), conexion);
                        }
                        pausar(lector);
                        break;
                    case 2:
                        /** Modificación de atributos de un vehículo existente */
                        System.out.print("Placa a modificar: ");
                        String pMod = lector.nextLine();
                        if (util.buscarVehiculo(pMod, conexion) != null) {
                            System.out.print("Nuevo Precio: ");
                            double nPrecio = Double.parseDouble(lector.nextLine());
                            System.out.print("Estado (1: Disponible / 2: Vendido): ");
                            int est = Integer.parseInt(lector.nextLine());
                            util.actualizarVehiculo(pMod, nPrecio, (est == 2 ? Estado_Vehiculo.Vendido : Estado_Vehiculo.Disponible), conexion);
                        } else {
                            System.out.println(">>> ERROR: Placa no encontrada.");
                        }
                        pausar(lector);
                        break;
                    case 3:
                        /** Eliminación física de un registro de vehículo */
                        System.out.print("Ingrese Placa para ELIMINAR: ");
                        String pDel = lector.nextLine();
                        util.eliminarVehiculo(pDel, conexion);
                        pausar(lector);
                        break;
                    case 4:
                        /** Visualización de todo el inventario */
                        System.out.println("\n--- INVENTARIO DE VEHÍCULOS ---");
                        util.obtenerVehiculos(conexion);
                        pausar(lector);
                        break;
                    case 5:
                        System.out.println(">>> Regresando al menú principal...");
                        break;
                    default:
                        System.out.println(">>> Opción no válida.");
                }
            } catch (Exception e) {
                /** Gestión de errores de entrada y formato */
                System.out.println(">>> ERROR: Entrada inválida.");
                pausar(lector);
            }
        } while (opcion != 5);
    } // Cierre del método ejecutarModulo
} // Cierre de la clase GestionVehiculo