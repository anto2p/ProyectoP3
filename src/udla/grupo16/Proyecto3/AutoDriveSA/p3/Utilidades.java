package udla.grupo16.Proyecto3.AutoDriveSA.p3;

import java.sql.*;

public class Utilidades {

    // --- CONEXIÓN A BASE DE DATOS ---
    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/autodrivesa";
        String user = "root";
        String pass = "sasa";
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println(">>> ERROR: Conexión fallida: " + e.getMessage());
            return null;
        }
    }

    // --- MAPEADORES INTERNOS ---
    private Vehiculo mapearVehiculo(ResultSet rs) throws SQLException {
        Vehiculo v = new Vehiculo(
                rs.getString("placa"),
                rs.getString("marca"),
                rs.getString("modelo"),
                rs.getDouble("precio")
        );
        v.setEstado(Estado_Vehiculo.valueOf(rs.getString("estado")));
        return v;
    }

    private Vendedor mapearVendedor(ResultSet rs) throws SQLException {
        return new Vendedor(
                rs.getInt("id_vendedor"),
                rs.getString("cedula"),
                rs.getString("nombre_completo"),
                rs.getDouble("anos_exp")
        );
    }

    // --- MÓDULO DE VEHÍCULOS ---
    public void insertarVehiculo(Vehiculo v, Connection conn) {
        String sql = "INSERT INTO vehiculo (placa, marca, modelo, precio, estado) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setDouble(4, v.getPrecio());
            ps.setString(5, v.getEstado().name());
            ps.executeUpdate();
            System.out.println(">>> Vehículo guardado con éxito.");
        } catch (SQLException e) {
            System.out.println(">>> ERROR al guardar: " + e.getMessage());
        }
    }

    public void obtenerVehiculos(Connection conn) {
        String sql = "SELECT * FROM vehiculo";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println(mapearVehiculo(rs).imprimir());
            }
        } catch (SQLException e) {
            System.out.println(">>> ERROR al listar: " + e.getMessage());
        }
    }

    public Vehiculo buscarVehiculo(String placa, Connection conn) {
        String sql = "SELECT * FROM vehiculo WHERE placa = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapearVehiculo(rs);
        } catch (SQLException e) {
            System.out.println(">>> ERROR al buscar: " + e.getMessage());
        }
        return null;
    }

    public void actualizarVehiculo(String placa, double precio, Estado_Vehiculo estado, Connection conn) {
        String sql = "UPDATE vehiculo SET precio = ?, estado = ? WHERE placa = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, precio);
            ps.setString(2, estado.name());
            ps.setString(3, placa);
            ps.executeUpdate();
            System.out.println(">>> Registro actualizado.");
        } catch (SQLException e) {
            System.out.println(">>> ERROR al actualizar: " + e.getMessage());
        }
    }

    public void eliminarVehiculo(String placa, Connection conn) {
        String sql = "DELETE FROM vehiculo WHERE placa = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, placa);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println(">>> Vehículo eliminado.");
            else System.out.println(">>> No se encontró la placa.");
        } catch (SQLException e) {
            System.out.println(">>> ERROR: No se puede eliminar un vehículo con historial de ventas.");
        }
    }

    public void mostrarInventarioPorEstado(Estado_Vehiculo estado, Connection conn) {
        String sql = "SELECT * FROM vehiculo WHERE estado = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, estado.name());
            ResultSet rs = ps.executeQuery();
            System.out.println("\n--- LISTADO DE AUTOS " + estado.name().toUpperCase() + "S ---");
            while (rs.next()) {
                System.out.println(mapearVehiculo(rs).imprimir());
            }
        } catch (SQLException e) {
            System.out.println(">>> ERROR: " + e.getMessage());
        }
    }

    // --- MÓDULO DE VENDEDORES ---
    public void insertarVendedor(Vendedor v, Connection conn) {
        String sql = "INSERT INTO vendedor (cedula, nombre_completo, anos_exp) VALUES (?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getCedula());
            ps.setString(2, v.getNombre_Completo());
            ps.setDouble(3, v.getAnos_exp());
            ps.executeUpdate();
            System.out.println(">>> Vendedor registrado.");
        } catch (SQLException e) {
            System.out.println(">>> ERROR: " + e.getMessage());
        }
    }

    public void obtenerVendedores(Connection conn) {
        String sql = "SELECT * FROM vendedor";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println(mapearVendedor(rs).imprimir());
            }
        } catch (SQLException e) {
            System.out.println(">>> ERROR: " + e.getMessage());
        }
    }

    public Vendedor buscarVendedorPorID(int id, Connection conn) {
        String sql = "SELECT * FROM vendedor WHERE id_vendedor = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapearVendedor(rs);
        } catch (SQLException e) {
            System.out.println(">>> ERROR: " + e.getMessage());
        }
        return null;
    }

    public void eliminarVendedor(int id, Connection conn) {
        String sql = "DELETE FROM vendedor WHERE id_vendedor = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println(">>> Vendedor eliminado.");
            else System.out.println(">>> ID no encontrado.");
        } catch (SQLException e) {
            System.out.println(">>> ERROR: El vendedor tiene ventas asociadas y no puede eliminarse.");
        }
    }

    // --- MÓDULO DE VENTAS ---
    public void insertarVenta(Venta v, Connection conn) {
        String sql = "INSERT INTO venta (montoTotal, vehiculo, vendedor) VALUES (?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, v.getMontoTotal());
            ps.setString(2, v.getVehiculo().getPlaca());
            ps.setInt(3, v.getVendedor().getIdVendedor());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(">>> ERROR al registrar venta: " + e.getMessage());
        }
    }

    public void obtenerVentas(Connection conn) {
        String sql = "SELECT v.idventa, v.montoTotal, " +
                "ve.placa, ve.marca, ve.modelo, ve.precio, ve.estado, " +
                "vend.id_vendedor, vend.cedula, vend.nombre_completo, vend.anos_exp " +
                "FROM venta v " +
                "JOIN vehiculo ve ON v.vehiculo = ve.placa " +
                "JOIN vendedor vend ON v.vendedor = vend.id_vendedor";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Vehiculo veh = mapearVehiculo(rs);
                Vendedor ven = mapearVendedor(rs);
                Venta venta = new Venta(rs.getInt("idventa"), veh, ven, rs.getDouble("montoTotal"));
                System.out.println(venta.imprimir());
            }
        } catch (SQLException e) {
            System.out.println(">>> ERROR al cargar ventas: " + e.getMessage());
        }
    }
}