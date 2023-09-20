import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJDBC {
    
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/base_de_datos";
        String user = "root";
        String password = "Eliana3132710953.";  

        Connection conexion = null;

        try {            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conexion = DriverManager.getConnection(url, user, password);
            
            if (conexion != null) {
                System.out.println("Conexión exitosa a la base de datos.");                
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
    }
}