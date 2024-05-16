
package sistemadegestionacademico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexion {
   
      // Datos de conexión sin contraseña
    private static final String URL = "jdbc:mariadb://127.0.0.1:3306/universidadulp";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Contraseña vacía

    /**
     
Método para obtener una conexión a la base de datos.
@return una conexión activa a la base de datos.
@throws SQLException si ocurre un error al conectar.
@throws ClassNotFoundException si el driver no se encuentra disponible.
*/
public static Connection getConexion() throws SQLException, ClassNotFoundException {// Cargar el driver de MariaDB
    Class.forName("org.mariadb.jdbc.Driver");// Establecer y devolver la conexión
    return DriverManager.getConnection(URL, USER, PASSWORD);}
}


