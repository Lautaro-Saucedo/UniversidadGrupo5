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
import java.util.*;
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
//        MateriaData md = new MateriaData(con);
//        
//        Materia materia1 = new Materia("test1",1,true);
//        Materia materia2 = new Materia("test2",1,true);
//        Materia materia3 = new Materia("test3",1,true);
//        
//        md.guardarMateria(materia1);
//        md.guardarMateria(materia2);
//        md.guardarMateria(materia3);
//        
//        LocalDate miFecha = LocalDate.of(2000,01,01);
//        LocalDate miFecha2 = LocalDate.of(1993,05,16);
//        LocalDate miFecha3 = LocalDate.of(2002,12,24);
//        
//        Alumno miAlumno1 = new Alumno("Nom1","Ap1",miFecha,32456789,true);
//        Alumno miAlumno2 = new Alumno("Nom2","Ap2",miFecha2,12345,true);
//        Alumno miAlumno3 = new Alumno("Nom3","Ap3",miFecha3,67890,true);
//        
//        AlumnoData alu1 = new AlumnoData(con);
//        alu1.guardarAlumno(miAlumno1);
//        AlumnoData alu2 = new AlumnoData(con);
//        alu2.guardarAlumno(miAlumno2);
//        AlumnoData alu3 = new AlumnoData(con);
//        alu3.guardarAlumno(miAlumno3);
//        
//        Cursada alu1mat1 = new Cursada(miAlumno1,materia1,0.0);
//        CursadaData curData = new CursadaData(con);
//        curData.inscribir(alu1mat1);
//        
//        Cursada alu1mat2 = new Cursada(miAlumno1,materia2,0.0);
//        CursadaData curData2 = new CursadaData(con);
//        curData2.inscribir(alu1mat2);
//        Cursada alu2mat1 = new Cursada(miAlumno2,materia1,0.0);
//        CursadaData curData3 = new CursadaData(con);
//        curData3.inscribir(alu2mat1);
//        Cursada alu2mat2 = new Cursada(miAlumno2,materia2,0.0);
//        CursadaData curData4 = new CursadaData(con);
//        curData4.inscribir(alu2mat2);
//        Cursada alu2mat3 = new Cursada(miAlumno2,materia3,0.0);
//        CursadaData curData5 = new CursadaData(con);
//        curData5.inscribir(alu2mat3);
//        Cursada alu3mat2 = new Cursada(miAlumno3,materia2,0.0);
//        CursadaData curData6 = new CursadaData(con);
//        curData6.inscribir(alu3mat2);
//        
        CursadaData x = new CursadaData(con);
        
        //List<Materia> lista = x.obtenerMateriasNOCursadas(4);
        //List<Materia> lista = x.obtenerMateriasCursadas(3);
        List<Cursada> lista = x.obtenerCursadasXAlumno(2);
        //List<Cursada> lista = x.obtenerCursadas();
        
//        for(Materia m:lista){
//            System.out.println(m);
//        }
        
        for(Cursada s:lista){
            System.out.println(s);
        }
        
        x.actualizarNotaCursada(2, 3, 9.5);
        System.out.println("-----------------------------------------");
        
        
        List<Cursada> lista2 = x.obtenerCursadasXAlumno(2);
        
        for(Cursada s:lista2){
            System.out.println(s);
        }
    }
    
}
