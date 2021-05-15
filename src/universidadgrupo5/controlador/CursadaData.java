
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
            PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);//LXW: Lito
            ps.setInt(1,cursada.getId_alumno().getId_alumno());
            ps.setInt(2,cursada.getId_materia().getId_materia());
            ps.setDouble(3,cursada.getNota());
            ps.execute();
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showInputDialog(null,"No se pudo inscribir");
        }
    }
    
    public List<Cursada> obtenerCursadas(){//LXW: Funcando
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
    
    public List<Cursada> obtenerCursadasXAlumno(int id_alumno){ //LXW: Funcando
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
    
    public List<Materia> obtenerMateriasCursadas(int id_alumno){ //LXW: Funcando!
        ArrayList<Materia> lista = new ArrayList<>();
        String query = "SELECT materia.id_materia , nombre_materia , año , estado FROM materia, cursada "
                + "WHERE materia.id_materia = cursada.id_materia AND cursada.id_alumno=?";
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Materia aux= new Materia();
                aux.setId_materia(rs.getInt("id_materia"));
                aux.setNombre_materia(rs.getString("nombre_materia"));
                aux.setAnio(rs.getInt("año"));
                aux.setEstado(rs.getBoolean("estado"));
                lista.add(aux);
            }
            ps.close();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "No se pudo obtener materias de este alumno");
        }
        return lista;
    }
    
    public List<Materia> obtenerMateriasNOCursadas(int id_alumno){//LXW: ya funca
        ArrayList<Materia> lista = new ArrayList<>();
        String query = "SELECT * FROM materia WHERE id_materia NOT IN(SELECT materia.id_materia FROM materia, "
                + "cursada WHERE materia.id_materia=cursada.id_materia AND cursada.id_alumno=?)";
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Materia aux= new Materia();
                aux.setId_materia(rs.getInt("id_materia"));
                aux.setNombre_materia(rs.getString("nombre_materia"));
                aux.setAnio(rs.getInt("año"));
                aux.setEstado(rs.getBoolean("estado"));
                lista.add(aux);
            }
            ps.close();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "No se pudo NO obtener materias de este alumno");
        }
        return lista;
    }
    
    public void borrarCursadaDeUnaMateriaDeUnAlumno(int id_alumno , int id_materia){//LXW: Funcando!
        String query = "DELETE FROM cursada WHERE id_alumno=? and id_materia=?";
        try{
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id_alumno);
            ps.setInt(2, id_materia);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar");
        }
    }
    
    public void actualizarNotaCursada(int id_alumno, int id_materia, double nota){ //LXW: Funca!
        String query="UPDATE cursada SET nota=? WHERE id_alumno=? AND id_materia=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setDouble(1,nota);
            ps.setInt(2, id_alumno);
            ps.setInt(3, id_materia);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException sqle){
            JOptionPane.showMessageDialog(null, "No se pudo actualizar");
        } 
    }
}
