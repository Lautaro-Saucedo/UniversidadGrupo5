package universidadgrupo5.modelo;

public class Cursada {
    private int id_cursada;
    private Alumno id_alumno;
    private Materia id_materia;
    private double nota;

    public Cursada() {
    }

    public Cursada(int id_cursada, Alumno id_alumno, Materia id_materia, double nota) {
        this.id_cursada = id_cursada;
        this.id_alumno = id_alumno;
        this.id_materia = id_materia;
        this.nota = nota;
    }

    public Cursada(Alumno id_alumno, Materia id_materia, double nota) {
        this.id_alumno = id_alumno;
        this.id_materia = id_materia;
        this.nota = nota;
    }

    public int getId_cursada() {
        return id_cursada;
    }

    public void setId_cursada(int id_cursada) {
        this.id_cursada = id_cursada;
    }

    public Alumno getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(Alumno id_alumno) {
        this.id_alumno = id_alumno;
    }

    public Materia getId_materia() {
        return id_materia;
    }

    public void setId_materia(Materia id_materia) {
        this.id_materia = id_materia;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    
    
    
}
