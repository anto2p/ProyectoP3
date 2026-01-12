package udla.grupo16.Proyecto3.AutoDriveSA.p3;

import java.util.Date;

/**
 * Define los atributos y la lógica de vinculación para una Venta.
 */
public class Venta implements Imprimir {
    /** Atributos de identificación, temporales y relacionales */
    private int idVenta;
    private Date fecha;
    private double montoTotal;
    private Vehiculo vehiculo;
    private Vendedor vendedor;

    /**
     * Constructor que vincula un vehículo con un vendedor y actualiza el estado del auto.
     */
    public Venta(int idVenta, Vehiculo vehiculo, Vendedor vendedor, double montoTotal) {
        this.idVenta = idVenta;
        this.fecha = new Date();
        this.vehiculo = vehiculo;
        this.vendedor = vendedor;
        this.montoTotal = montoTotal;
        /** Cambio de estado automático al instanciar la venta */
        if (this.vehiculo != null) {
            this.vehiculo.setEstado(Estado_Vehiculo.Vendido);
        }
    }

    /** Métodos de acceso (Getters y Setters) */
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(double montoTotal) { this.montoTotal = montoTotal; }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public Vendedor getVendedor() { return vendedor; }
    public void setVendedor(Vendedor vendedor) { this.vendedor = vendedor; }

    /**
     * Implementación de la interfaz Imprimir para la generación de facturas en consola.
     */
    @Override
    public String imprimir() {
        StringBuilder sb = new StringBuilder("Transacción de Venta #");
        sb.append(this.idVenta).append("\n")
                .append("Estado : ").append("REGISTRADA").append("\n")
                .append("Fecha : ").append(this.fecha).append("\n")
                .append("Monto Total : $").append(this.montoTotal).append("\n")
                .append("Venta vinculada al Vehículo [")
                .append(this.vehiculo.getPlaca()).append("] y gestionada por el Vendedor [")
                .append(this.vendedor.getNombre_Completo()).append("].\n");
        return sb.toString();
    }
}