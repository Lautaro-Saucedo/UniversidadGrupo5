/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo5.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            rs.next();
            m.setId_materia(rs.getInt(1));
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
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
            rs.next();
            aux.setId_materia(rs.getInt(1));
            aux.setNombre_materia(rs.getString(2));
            aux.setAnio(rs.getInt(3));
            aux.setEstado(rs.getBoolean(4));
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
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
 
    
    public void borrarMateriaF(int id){
        String query = "DELETE FROM materia WHERE id_materia=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException sqle){
            JOptionPane.showMessageDialog(null, "Error de conexion.");
        }
    }
    
    public void borrarMateriaL(int id){
        String query = "UPDATE materia SET estado=false WHERE id_materia=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException sqle){
            JOptionPane.showMessageDialog(null, "Error de conexion.");
        }
        
    }
}
