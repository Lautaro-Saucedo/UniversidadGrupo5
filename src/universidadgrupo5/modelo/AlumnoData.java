/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo5.modelo;
//prueba
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author br1st
 */
public class AlumnoData {
    private Connection conexion = null;//inicializado en null x si no se puede conectar
    
    public AlumnoData(Conexion conexion){
       
            this.conexion = conexion.getConexion();
       
    }
    
    public void guardarAlumno(Alumno a){
        String query = "INSERT INTO alumno( nombre, apellido, fecha_nac, legajo, estado) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
           
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setDate(3, Date.valueOf(a.getFecha_nac()));
            ps.setLong(4, a.getLegajo());
            ps.setBoolean(5, a.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                a.setId_alumno(rs.getInt(1));
                
            }else{
                System.out.println("No se pudo obtener el ID");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Alumno buscarAlumno(long legajo){
        Alumno a = new Alumno();
        String query = "SELECT id_alumno, nombre, apellido, fecha_nac, legajo, estado FROM alumno WHERE legajo=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setLong(5, legajo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                
                a.setId_alumno(rs.getInt("id_alumno"));
                a.setNombre(rs.getString("nombre"));
                a.setApellido(rs.getString("apellido"));
                a.setFecha_nac(rs.getDate("fecha_nac").toLocalDate());
                a.setLegajo(rs.getLong("legajo"));
                a.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    public List<Alumno> listarAlumno(int legajo){
        List<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT id_alumno, nombre, apellido, fecha_nac, legajo, estado FROM alumno WHERE 1";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Alumno a = new Alumno();
                a.setId_alumno(rs.getInt("id_alumno"));
                a.setNombre(rs.getString("nombre"));
                a.setApellido(rs.getString("apellido"));
                a.setFecha_nac(rs.getDate("fecha_nac").toLocalDate());
                a.setLegajo(rs.getLong("legajo"));
                a.setEstado(rs.getBoolean("estado"));
                
                alumnos.add(a);
                
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumnos;
    }
    
    public void actualizarAlumno(Alumno a){
        String query = "UPDATE alumno SET nombre=?,apellido=?,fecha_nac=?,legajo=?,estado=? WHERE id_alumno=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(6, a.getId_alumno());
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setDate(3, Date.valueOf(a.getFecha_nac()));
            ps.setLong(4, a.getLegajo());
            ps.setBoolean(5, a.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarAlumno(int id){
        String query = "DELETE FROM alumno WHERE id_alumno=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
