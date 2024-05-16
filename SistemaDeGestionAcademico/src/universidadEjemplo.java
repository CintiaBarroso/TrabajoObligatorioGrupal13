import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.Conexion;

public class universidadEjemplo {

    public static void main(String[] args) {
        Connection connection = Conexion.getConnection();
        // Insertar 3 alumnos
        insertAlumno(connection, 1, "Juan", "Pérez", "2000-01-01", true);
        insertAlumno(connection, 2, "María", "Gómez", "2001-02-02", true);
        insertAlumno(connection, 3, "Carlos", "López", "2002-03-03", true);
        // Insertar 2 materias
        insertMateria(connection, 1, "Matemáticas", 2023, true);
        insertMateria(connection, 2, "Historia", 2023, true);
        // Inscribir a los alumnos en 2 materias cada uno
        inscribirAlumno(connection, 1, 1, 8);
        inscribirAlumno(connection, 1, 2, 9);
        inscribirAlumno(connection, 2, 1, 7);
        inscribirAlumno(connection, 2, 2, 10);
        inscribirAlumno(connection, 3, 1, 6);
        inscribirAlumno(connection, 3, 2, 5);
        // Listar los datos de los alumnos con calificaciones superiores a 8
        listarAlumnosConNotasSuperioresA(connection, 8);
        // Desinscribir un alumno de una de las materias
        desinscribirAlumno(connection, 3, 2);
    }

    // Verificar existencia antes de insertar para evitar duplicados
private static void insertAlumno(Connection connection, int id, String nombre, String apellido, String fechaNacimiento, boolean estado) {
    String queryCheck = "SELECT COUNT(*) FROM alumno WHERE idAlumno = ?";
    String queryInsert = "INSERT INTO alumno (idAlumno, nombre, apellido, fechaNacimiento, estado) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement statementCheck = connection.prepareStatement(queryCheck)) {
        statementCheck.setInt(1, id);
        ResultSet rs = statementCheck.executeQuery();
        if (rs.next() && rs.getInt(1) == 0) {
            try (PreparedStatement statementInsert = connection.prepareStatement(queryInsert)) {
                statementInsert.setInt(1, id);
                statementInsert.setString(2, nombre);
                statementInsert.setString(3, apellido);
                statementInsert.setString(4, fechaNacimiento);
                statementInsert.setBoolean(5, estado);
                statementInsert.executeUpdate();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    private static void insertMateria(Connection connection, int id, String nombre, int año, boolean estado) {
        String query = "INSERT INTO materia (idMateria, nombre, año, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, nombre);
            statement.setInt(3, año);
            statement.setBoolean(4, estado);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void inscribirAlumno(Connection connection, int idAlumno, int idMateria, int nota) {
        String query = "INSERT INTO inscripcion (idAlumno, idMateria, nota) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idAlumno);
            statement.setInt(2, idMateria);
            statement.setInt(3, nota);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void listarAlumnosConNotasSuperioresA(Connection connection, int notaMinima) {
        String query = "SELECT a.idAlumno, a.nombre, a.apellido, i.nota FROM alumno a " +
                       "JOIN inscripcion i ON a.idAlumno = i.idAlumno WHERE i.nota > ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, notaMinima);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idAlumno = resultSet.getInt("idAlumno");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                int nota = resultSet.getInt("nota");
                System.out.println("ID: " + idAlumno + ", Nombre: " + nombre + ", Apellido: " 
                        + apellido + ", Nota: " + nota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void desinscribirAlumno(Connection connection, int idAlumno, int idMateria) {
        String query = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idAlumno);
            statement.setInt(2, idMateria);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
