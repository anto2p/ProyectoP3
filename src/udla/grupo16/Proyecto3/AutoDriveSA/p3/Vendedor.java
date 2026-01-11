package udla.grupo16.Proyecto3.AutoDriveSA.p3;

public class Vendedor implements Imprimir {
    private int idVendedor;       // ID Autoincremental de la base de datos
    private String cedula;        // Nuevo campo identificador
    private String Nombre_Completo;
    private double anos_exp;

    // CONSTRUCTOR 1: Para crear un vendedor nuevo
    public Vendedor(String cedula, String nombre_Completo, double anos_exp) {
        this.cedula = cedula;
        this.Nombre_Completo = nombre_Completo;
        this.anos_exp = anos_exp;
    }

    // CONSTRUCTOR 2: Para cuando cargamos datos desde la Base de Datos
    public Vendedor(int idVendedor, String cedula, String nombre_Completo, double anos_exp) {
        this.idVendedor = idVendedor;
        this.cedula = cedula;
        this.Nombre_Completo = nombre_Completo;
        this.anos_exp = anos_exp;
    }

    // --- GETTERS Y SETTERS ---

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre_Completo() {
        return Nombre_Completo;
    }

    public void setNombre_Completo(String nombre_Completo) {
        Nombre_Completo = nombre_Completo;
    }

    public double getAnos_exp() {
        return anos_exp;
    }

    public void setAnos_exp(double anos_exp) {
        this.anos_exp = anos_exp;
    }

    // --- MÉTODO IMPRIMIR (POLIMORFISMO) ---
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