package universidadgrupo5.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import universidadgrupo5.modelo.Conexion;
import universidadgrupo5.modelo.Materia;
/**
 *
 * @author Laucha
 */
public class MateriaData {
    private Connection con=null;
    
    public MateriaData(Conexion c){
        con=c.getConexion();
    }
    
    public void guardarMateria(Materia m){
        String query = "INSERT INTO materia VALUES (NULL,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getNombre_materia());
            ps.setInt(2, m.getAnio());
            ps.setBoolean(3, m.getEstado());
            ps.executeQuery();
            ResultSet rs=ps.getGeneratedKeys();
            if (rs.next()){
                m.setId_materia(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Materia guardada con exito.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion.");
        }
    }
    
    public List<Materia> listarMaterias(){
        ArrayList<Materia> lista = new ArrayList<>();
        String query="SELECT * FROM materia";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Materia aux= new Materia();
                aux.setId_materia(rs.getInt(1));
                aux.setNombre_materia(rs.getString(2));
                aux.setAnio(rs.getInt(3));
                aux.setEstado(rs.getBoolean(4));
                lista.add(aux);
            }
            ps.close();
        } catch (SQLException sqle){
            JOptionPane.showMessageDialog(null, "Error de conexion.");
        }
        
        return lista;
    }
    
    public Materia buscarMateria(int id){
        Materia aux=new Materia();
        String query="SELECT * FROM materia WHERE id_materia=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                aux.setId_materia(rs.getInt(1));
                aux.setNombre_materia(rs.getString(2));
                aux.setAnio(rs.getInt(3));
                aux.setEstado(rs.getBoolean(4));
            } else {
                JOptionPane.showMessageDialog(null, "No existe una materia con ese ID.");
            }
            ps.close();
        } catch (SQLException sqle){
            JOptionPane.showMessageDialog(null, "Error de conexion.");
        }
        return aux;
    }
    
    public void actualizarMateria(Materia m){
        String query="UPDATE materia SET nombre_materia=?, año=? WHERE id_materia=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,m.getNombre_materia());
            ps.setInt(2,m.getAnio());
            ps.setInt(3, m.getId_materia());
            if(ps.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null, "La materia se modificó correctamente");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar materia");
        } 
    }
 
    
    public void borrarMateriaF(int id){
        String query = "DELETE FROM materia WHERE id_materia=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            if (ps.executeUpdate()==1){
                JOptionPane.showMessageDialog(null, "Borrado con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "La materia que intenta borrar no existe.");
            }
            ps.close();
        } catch (SQLException sqle){
            JOptionPane.showMessageDialog(null, "Error al borrar. Asegúrese de que la materia no tenga ningun alumno inscripto antes de intentar eliminarla permanentemente.");
        }
    }
    
    public void borrarMateriaL(int id){
        String query = "UPDATE materia SET estado=false WHERE id_materia=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            if (ps.executeUpdate()==1){
                JOptionPane.showMessageDialog(null, "Materia deshabilitada.");
            } else {
                JOptionPane.showMessageDialog(null, "La materia que intenta actualizar no existe.");
            }
            ps.close();
        } catch (SQLException sqle){
            JOptionPane.showMessageDialog(null, "Error de conexion.");
        }
        
    }
    
    public void restaurarMateria(int id){
        String query = "UPDATE materia SET estado=true WHERE id_materia=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            if (ps.executeUpdate()==1){
                JOptionPane.showMessageDialog(null, "Materia habilitada.");
            } else {
                JOptionPane.showMessageDialog(null, "La materia que intenta actualizar no existe.");
            }
            ps.close();
        } catch (SQLException sqle){
            JOptionPane.showMessageDialog(null, "Error de conexion.");
        }
        
    }
}
