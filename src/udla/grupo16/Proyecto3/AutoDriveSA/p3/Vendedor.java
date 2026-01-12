package udla.grupo16.Proyecto3.AutoDriveSA.p3;

/**
 * Define los atributos y comportamiento de la entidad Vendedor.
 */
public class Vendedor implements Imprimir {
    /** Atributos de identificación y perfil profesional */
    private int idVendedor;
    private String cedula;
    private String Nombre_Completo;
    private double anos_exp;

    /**
     * Constructor para instancias nuevas sin ID asignado.
     */
    public Vendedor(String cedula, String nombre_Completo, double anos_exp) {
        this.cedula = cedula;
        this.Nombre_Completo = nombre_Completo;
        this.anos_exp = anos_exp;
    }

    /**
     * Constructor para reconstruir objetos desde la base de datos.
     */
    public Vendedor(int idVendedor, String cedula, String nombre_Completo, double anos_exp) {
        this.idVendedor = idVendedor;
        this.cedula = cedula;
        this.Nombre_Completo = nombre_Completo;
        this.anos_exp = anos_exp;
    }

    /** Métodos de acceso (Getters y Setters) */
    public int getIdVendedor() { return idVendedor; }
    public void setIdVendedor(int idVendedor) { this.idVendedor = idVendedor; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre_Completo() { return Nombre_Completo; }
    public void setNombre_Completo(String nombre_Completo) { Nombre_Completo = nombre_Completo; }

    public double getAnos_exp() { return anos_exp; }
    public void setAnos_exp(double anos_exp) { this.anos_exp = anos_exp; }

    /**
     * Implementación de la interfaz Imprimir para visualización del personal.
     */
    @Override
    public String imprimir() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------------\n")
                .append("ID INTERNO      : ").append(this.idVendedor).append("\n")
                .append("CÉDULA          : ").append(this.cedula).append("\n")
                .append("NOMBRE          : ").append(this.Nombre_Completo).append("\n")
                .append("AÑOS EXP.       : ").append(this.anos_exp).append("\n")
                .append("-----------------------------");
        return sb.toString();
    }
}