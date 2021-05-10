/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo5;

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
    }
    
}
