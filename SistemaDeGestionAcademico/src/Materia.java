public class Materia {
    // Atributos de la materia
    private int id;           // Identificador único de la materia
    private String nombre;    // Nombre de la materia
    private int año;          // Año en el que se imparte la materia
    private boolean estado;   // Estado de la materia (activa o no)

    // Constructor de la clase Materia
    public Materia(int id, String nombre, int año, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.año = año;
        this.estado = estado;
    }

    // Getters y setters - Métodos para obtener y modificar los valores de los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
