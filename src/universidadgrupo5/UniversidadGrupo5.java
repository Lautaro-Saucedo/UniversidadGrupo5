/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo5;

import universidadgrupo5.controlador.AlumnoData;
import universidadgrupo5.controlador.CursadaData;
import universidadgrupo5.controlador.MateriaData;
import universidadgrupo5.modelo.Conexion;


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
        AlumnoData ad = new AlumnoData(con);
        MateriaData md = new MateriaData(con);
        CursadaData cd = new CursadaData(con);
        
        
        
        
    }
    
}
