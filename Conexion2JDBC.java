import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Conexion2JDBC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la URL de la base de datos: ");
        String url = scanner.nextLine();

        System.out.print("Ingrese el nombre de usuario: ");
        String user = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine();

        Connection conexion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, password);

            if (conexion != null) {
                System.out.println("Conexión exitosa a la base de datos.");                
                
                mostrarDatos(conexion);
            } else {
                System.out.println("La conexión a la base de datos ha fallado.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el controlador JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                    System.out.println("Conexión cerrada correctamente.");
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        scanner.close();
    }

    public static void mostrarDatos(Connection conexion) {
        try {
            Statement statement = conexion.createStatement();
            String consultaSQL = "SELECT * FROM clientes";

            ResultSet resultado = statement.executeQuery(consultaSQL);

            while (resultado.next()) {
                int columna1 = resultado.getInt("ID_Cliente");
                String columna2 = resultado.getString("Nombre_Cliente"); 
                String columna3 = resultado.getString("Dirección_Cliente"); 
                String columna4 = resultado.getString("Teléfono_Cliente"); 
                
                System.out.println("ID_Cliente: " + columna1 + ", Nombre_Cliente: " + columna2 + ", Dirección_Cliente: " + columna3 + ", Teléfono_Cliente: " + columna4);
            }

            resultado.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
    }
}