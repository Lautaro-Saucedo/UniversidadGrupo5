
package universidadgrupo5.controlador;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import universidadgrupo5.modelo.Alumno;
import universidadgrupo5.modelo.Conexion;
import universidadgrupo5.modelo.Cursada;
import universidadgrupo5.modelo.Materia;

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
            PreparedStatement ps = conexion.prepareStatement(query);//Laucha: recuperar la clave generada por si las moscas
            ps.setInt(1,cursada.getId_alumno().getId_alumno());
            ps.setInt(2,cursada.getId_materia().getId_materia());
            ps.setDouble(3,cursada.getNota());
            ps.execute();
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showInputDialog(null,"No se pudo inscribir");
        }
    }
    
    public List<Cursada> obtenerCursadas(){
        ArrayList<Cursada> lista = new ArrayList<>();
        String query = "SELECT * FROM cursada";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cursada aux= new Cursada();
                Alumno alu = new Alumno();
                Materia mat = new Materia();
                aux.setId_cursada(rs.getInt("id_cursada"));
                alu.setId_alumno(rs.getInt("id_alumno"));
                aux.setId_alumno(alu);
                mat.setId_materia(rs.getInt("id_materia"));
                aux.setId_materia(mat);
                aux.setNota(rs.getInt("nota"));
                lista.add(aux);
            }
            ps.close();
        } catch (SQLException sqle){
            JOptionPane.showMessageDialog(null, "No se pudo ostener cursadas");
        }
        return lista;
    }
    
    public List<Cursada> obtenerCursadasXAlumno(int id_alumno){
        ArrayList<Cursada> lista = new ArrayList<>();
        String query="SELECT * FROM cursada WHERE id_alumno=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Alumno alu = new Alumno();
                Materia mat = new Materia();
                Cursada aux= new Cursada();
                aux.setId_cursada(rs.getInt("id_cursada"));
                alu.setId_alumno(rs.getInt("id_alumno"));
                aux.setId_alumno(alu);
                mat.setId_materia(rs.getInt("id_materia"));
                aux.setId_materia(mat);
                aux.setNota(rs.getInt("nota"));
                lista.add(aux);
            }
            ps.close();
        } catch (SQLException sqle){
            JOptionPane.showMessageDialog(null, "No se pudo obtener cursadas de ese alumno");
        }
        return lista;
    }
    
    public List<Materia> obtenerMateriasCursadas(int id_alumno){
        ArrayList<Materia> lista = new ArrayList<>();
        String query = "SELECT id_materia FROM cursada WHERE id_alumno=?";
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Materia aux= new Materia();
                aux.setId_materia(rs.getInt("id_materia"));
                lista.add(aux);
            }
            ps.close();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "No se pudo obtener materias de ese alumno");
        }
        return lista;
    }
    
    public List<Materia> obtenerMateriasNOCursadas(int id_alumno){
        //Laucha: no funciona, esto estaria devolviendo las materias cursadas por todos los alumnos menos el que le pasaste por parametro.
        //aca habria que obtener todas las id_materia de la tabla materia,compararla con cursada y devolver las id que no estan.
        ArrayList<Materia> lista = new ArrayList<>();
        String query = "SELECT id_materia FROM cursada WHERE NOT id_alumno=?";
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Materia aux= new Materia();
                aux.setId_materia(rs.getInt("id_materia"));
                lista.add(aux);
            }
            ps.close();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "No se pudo obtener materias de ese alumno");
        }
        return lista;
    }
    
    public void borrarCursadaDeUnaMateriaDeUnAlumno(int id_alumno , int id_materia){
        //este query esta mal, es DELETE FROM (tabla) WHERE (condiciones)
        String query = "DELETE cursada FROM cursada WHERE id_alumno=? , id_materia=?";
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(id_alumno, id_alumno);
            ps.setInt(id_materia, id_materia);
            ps.execute();
            ps.close();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar");
        }
    }
    
    public void actualizarNotaCursada(int id_Materia, int id_alumno, int nota){
        String query="UPDATE cursada SET nota=? WHERE id_materia=?, id_alumno=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(nota,nota);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException sqle){
            JOptionPane.showMessageDialog(null, "No se pudo actualizar");
        } 
    }
}
