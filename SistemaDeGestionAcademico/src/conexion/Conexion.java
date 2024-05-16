
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexion {
   
      // Datos de conexión sin contraseña
    private static final String URL = "jdbc:mariadb://127.0.0.1:3306/universidadulp";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 
    private static Connection connection;

   
public static Connection getConnection() {
 if (connection == null) {
 try {
 connection = DriverManager.getConnection(URL, USER, PASSWORD);
 } catch (SQLException e) {
 e.printStackTrace();
 }
 }
 return connection;
}
}


