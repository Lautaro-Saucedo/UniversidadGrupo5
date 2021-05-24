
package universidadgrupo5.modelo;

import java.time.LocalDate;

public class Alumno {
    private int id_alumno;
    private String nombre;
    private String apellido;
    private LocalDate fecha_nac;
    private long legajo;
    private boolean estado;

    public Alumno() {
    }

    public Alumno(int id_alumno, String nombre, String apellido, LocalDate fecha_nac, long legajo, boolean estado) {
        this.id_alumno = id_alumno;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nac = fecha_nac;
        this.legajo = legajo;
        this.estado = estado;
    }

    public Alumno(String nombre, String apellido, LocalDate fecha_nac, long legajo, boolean estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nac = fecha_nac;
        this.legajo = legajo;
        this.estado = estado;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public long getLegajo() {
        return legajo;
    }

    public void setLegajo(long legajo) {
        this.legajo = legajo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ID:"+id_alumno+" - "+apellido+" "+nombre+" - Legajo:"+legajo+" - Fecha de nacimiento:"+fecha_nac+" - Estado:"+estado;
    }
}
