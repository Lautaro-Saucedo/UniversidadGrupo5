/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo5;

import universidadgrupo5.controlador.MateriaData;
import universidadgrupo5.controlador.CursadaData;
import universidadgrupo5.controlador.AlumnoData;
import java.time.LocalDate;
import java.time.Month;
import universidadgrupo5.modelo.*;

/**
 *
 * @author Laucha
 */
public class UniversidadGrupo5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion con = new Conexion();
        con.getConexion();
        MateriaData md = new MateriaData(con);
        
        Materia m = new Materia("test1",1,true);
        
        md.guardarMateria(m);
        LocalDate miFecha = LocalDate.of(2000,01,01);
        
        Alumno miAlumno = new Alumno("Nom1","Ap1",miFecha,32456789,true);
        AlumnoData asdf = new AlumnoData(con);
        asdf.guardarAlumno(miAlumno);
        
        Cursada cur = new Cursada(miAlumno,m,0.0);
        CursadaData curData = new CursadaData(con);
        curData.inscribir(cur);
        LocalDate fecha = LocalDate.of(2000, 01, 01);
        
        Alumno yo = new Alumno("Bruno","Sturniolo",fecha,36046044,true);
        Alumno jero = new Alumno("Jeronimo","Sturniolo",LocalDate.of(1991, 11, 13),44357758,true);
        AlumnoData ad = new AlumnoData(con);
        ad.guardarAlumno(yo);
        ad.guardarAlumno(jero);
        
        System.out.println(ad.buscarAlumno(36046044).getNombre());
        
        CursadaData cd = new CursadaData(con);
    }
    
}
