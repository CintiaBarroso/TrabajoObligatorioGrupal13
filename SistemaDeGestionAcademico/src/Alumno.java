public class Alumno {
    // Atributos del alumno
    private int id;                  // Identificador único del alumno
    private String nombre;           // Nombre del alumno
    private String apellido;         // Apellido del alumno
    private String fechaNacimiento;  // Fecha de nacimiento del alumno
    private boolean estado;          // Estado del alumno (activo o no)

    // Constructor de la clase Alumno
    public Alumno(int id, String nombre, String apellido, String fechaNacimiento, boolean estado) {
        this.id = id;                         // Asigna el id
        this.nombre = nombre;                 // Asigna el nombre
        this.apellido = apellido;             // Asigna el apellido
        this.fechaNacimiento = fechaNacimiento; // Asigna la fecha de nacimiento
        this.estado = estado;                 // Asigna el estado (activo o no)
    }

    // Getters y setters - Métodos para obtener y modificar los valores de los atributos

    public int getId() {
        return id;  // Obtiene el id del alumno
    }

    public void setId(int id) {
        this.id = id;  // Establece el id del alumno
    }

    public String getNombre() {
        return nombre;  // Obtiene el nombre del alumno
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;  // Establece el nombre del alumno
    }

    public String getApellido() {
        return apellido;  // Obtiene el apellido del alumno
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;  // Establece el apellido del alumno
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;  // Obtiene la fecha de nacimiento del alumno
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;  // Establece la fecha de nacimiento del alumno
    }

    public boolean isEstado() {
        return estado;  // Obtiene el estado del alumno
    }

    public void setEstado(boolean estado) {
        this.estado = estado;  // Establece el estado del alumno
    }
}
