package udla.grupo16.Proyecto3.AutoDriveSA.p3;

import java.sql.Connection;
import java.util.Scanner;

public interface Gestionable {

    public void ejecutarModulo(Scanner lector, Utilidades util, Connection conexion);

    /**
     * Metodo por defecto para el control de flujo en consola.
     */
    default void pausar(Scanner lector) {
        System.out.println("\n>>> Presione ENTER para continuar...");
        lector.nextLine();
    }
}