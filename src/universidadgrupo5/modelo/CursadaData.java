
package universidadgrupo5.modelo;

import java.sql.*;
import java.util.*;

/**
 *
 * @author LXWeber
 */
public class CursadaData {
    private Connection conexion = null;
    
    public CursadaData(Conexion conexion){
        this.conexion=conexion.getConexion();
    }
    
    public void inscribir(Cursada cursada){
        String query = "INSERT INTO cursada(id_alumno,id_materia, nota) VALUES (?,?,?)";
        
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1,cursada.getAlumno().getId_alumno());
            ps.setInt(2,cursada.getMateria().getId_materia());
            ps.setDouble(3,cursada.getNota());
            ps.execute();
            ps.close();
        }catch(SQLException ex){
            
        }
    }
}
