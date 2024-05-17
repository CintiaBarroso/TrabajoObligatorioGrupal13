import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.Conexion;

public class universidadEjemplo {

    public static void main(String[] args) {
        Connection connection = Conexion.getConnection();
        // Limpiar tablas - Vamos a empezar de cero
        limpiarTablas(connection);
        // Insertar 3 alumnos - Añadimos a Juan, María y Carlos
        insertAlumno(connection, 1, 12345678, "Juan", "Pérez", "2000-01-01", true);
        insertAlumno(connection, 2, 23456789, "María", "Gómez", "2001-02-02", true);
        insertAlumno(connection, 3, 34567890, "Carlos", "López", "2002-03-03", true);
        // Insertar 4 materias - Añadimos Matemáticas, Historia, Química y Física
        insertMateria(connection, 1, "Matemáticas", 2023, true);
        insertMateria(connection, 2, "Historia", 2023, true);
        insertMateria(connection, 3, "Química", 2023, true);
        insertMateria(connection, 4, "Física", 2023, true);
        // Inscribir a los alumnos en 2 materias cada uno - Asignamos materias a los alumnos con sus respectivas notas
        inscribirAlumno(connection, 1, 1, 8); // Juan en Matemáticas con 8
        inscribirAlumno(connection, 1, 2, 9); // Juan en Historia con 9
        inscribirAlumno(connection, 2, 3, 7); // María en Química con 7
        inscribirAlumno(connection, 2, 4, 10); // María en Física con 10
        inscribirAlumno(connection, 3, 1, 6); // Carlos en Matemáticas con 6
        inscribirAlumno(connection, 3, 4, 5); // Carlos en Física con 5
        // Listar los datos de los alumnos con calificaciones superiores a 8 - Mostramos a los alumnos que sacaron más de 8
        listarAlumnosConNotasSuperioresA(connection, 8);
        // Desinscribir un alumno de una de las materias - Carlos se da de baja en Física
        desinscribirAlumno(connection, 3, 4);
    }

    // Limpiamos todas las tablas para empezar frescos
    private static void limpiarTablas(Connection connection) {
        String[] queries = {
            "DELETE FROM inscripcion",
            "DELETE FROM materia",
            "DELETE FROM alumno"
        };
        for (String query : queries) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para insertar un alumno, primero chequea si ya existe
    private static void insertAlumno(Connection connection, int id, int dni, String nombre, String apellido, String fechaNacimiento, boolean estado) {
        String queryCheck = "SELECT COUNT(*) FROM alumno WHERE idAlumno = ?";
        String queryInsert = "INSERT INTO alumno (idAlumno, dni, nombre, apellido, fechaNacimiento, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statementCheck = connection.prepareStatement(queryCheck)) {
            statementCheck.setInt(1, id);
            ResultSet rs = statementCheck.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) { // Si no existe, lo insertamos
                try (PreparedStatement statementInsert = connection.prepareStatement(queryInsert)) {
                    statementInsert.setInt(1, id);
                    statementInsert.setInt(2, dni);
                    statementInsert.setString(3, nombre);
                    statementInsert.setString(4, apellido);
                    statementInsert.setString(5, fechaNacimiento);
                    statementInsert.setBoolean(6, estado);
                    statementInsert.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para insertar una materia
    private static void insertMateria(Connection connection, int id, String nombre, int año, boolean estado) {
        String query = "INSERT INTO materia (idMateria, nombre, año, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, nombre);
            statement.setInt(3, año);
            statement.setBoolean(4, estado);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar materia: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para inscribir a un alumno en una materia con una nota
    private static void inscribirAlumno(Connection connection, int idAlumno, int idMateria, int nota) {
        String query = "INSERT INTO inscripcion (idAlumno, idMateria, nota) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idAlumno);
            statement.setInt(2, idMateria);
            statement.setInt(3, nota);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al inscribir alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Listamos a los alumnos con notas superiores a cierto valor
    private static void listarAlumnosConNotasSuperioresA(Connection connection, int notaMinima) {
        String query = "SELECT a.idAlumno, a.nombre, a.apellido, i.nota FROM alumno a "
                + "JOIN inscripcion i ON a.idAlumno = i.idAlumno WHERE i.nota > ?";
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

    // Desinscribir a un alumno de una materia específica
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
