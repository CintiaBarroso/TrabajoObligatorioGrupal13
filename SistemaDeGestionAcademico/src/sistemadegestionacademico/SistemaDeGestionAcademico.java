/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemadegestionacademico;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SistemaDeGestionAcademico {

    
    public static void main(String[] args) {
         try (Connection conn = Conexion.getConexion()) {
            System.out.println("Conexión establecida con éxito.");

            // Insertar 3 empleados
            insertarAlumno(conn, "alumno 1");
            insertarAlumno(conn, "alumno 2");
            insertarAlumno(conn, "alumno 3");

            // Insertar 2 herramientas
            insertarMateria(conn, "materia 1", 15);
            insertarMateria(conn, "materia 2", 20);
            
            
            //Insertar inscripciones
            
            
            // Listar todas las herramientas con stock superior a 10
            listarHerramientas(conn);

            // Dar de baja al primer empleado ingresado a la base de datos
            darDeBajaPrimerEmpleado(conn);

        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver de MariaDB: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
        
    }

    

