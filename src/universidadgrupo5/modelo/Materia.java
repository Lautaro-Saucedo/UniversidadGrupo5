package universidadgrupo5.modelo;

public class Materia {
    private int id_materia;
    private String nombre_materia;
    private int anio;
    private boolean estado;

    public Materia() {
    }

    public Materia(int id_materia, String nombre_materia, int anio, boolean estado) {
        this.id_materia = id_materia;
        this.nombre_materia = nombre_materia;
        this.anio = anio;
        this.estado = estado;
    }

    public Materia(String nombre_materia, int anio, boolean estado) {
        this.nombre_materia = nombre_materia;
        this.anio = anio;
        this.estado = estado;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombre_materia() {
        return nombre_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Materia{" + "id_materia=" + id_materia + ", nombre_materia=" + nombre_materia + ", anio=" + anio + ", estado=" + estado + '}';
    }
    
    
    
}
