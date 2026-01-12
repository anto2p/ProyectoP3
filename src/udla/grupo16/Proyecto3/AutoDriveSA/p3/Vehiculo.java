package udla.grupo16.Proyecto3.AutoDriveSA.p3;

/**
 * Define los atributos y comportamiento de la entidad Vehículo.
 */
public class Vehiculo implements Imprimir {
    /** Atributos de identificación y características del vehículo */
    private String placa;
    private String marca;
    private String modelo;
    private double precio;
    private Estado_Vehiculo estado;

    /**
     * Constructor para inicializar un vehículo con estado predeterminado 'Disponible'.
     */
    public Vehiculo(String placa, String marca, String modelo, double precio) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.estado = Estado_Vehiculo.Disponible;
    }

    /** Métodos de acceso (Getters y Setters) */
    public Estado_Vehiculo getEstado() { return estado; }
    public void setEstado(Estado_Vehiculo estado) { this.estado = estado; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    /**
     * Implementación de la interfaz Imprimir para visualización de datos.
     */
    @Override
    public String imprimir() {
        StringBuilder sb = new StringBuilder("Nombre : ");
        sb.append(this.marca).append("\n")
                .append("Estado : ").append(this.estado).append("\n")
                .append("Placa: ").append(this.placa).append("\n")
                .append("Modelo: ").append(this.modelo).append("\n")
                .append(" | Precio: $").append(this.precio).append("\n");
        return sb.toString();
    }
}