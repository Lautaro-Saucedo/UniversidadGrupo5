/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo5.controlador;
//prueba
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import universidadgrupo5.modelo.Alumno;
import universidadgrupo5.modelo.Conexion;

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
            if(ps.executeUpdate() == 1){//esta linea esta dando error al duplicar alumnos
                JOptionPane.showMessageDialog(null, "Alumno guardado correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo guardar,legajo repetido");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                a.setId_alumno(rs.getInt(1));//le seteo el id que me devuelve mariadb
                
            }else{
                System.out.println("No se pudo obtener el ID");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar alumno");
        }
    }
    
    public Alumno buscarAlumno(long legajo){
        Alumno a = new Alumno();
        String query = "SELECT id_alumno, nombre, apellido, fecha_nac, legajo, estado FROM alumno WHERE legajo=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setLong(1, legajo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                
                a.setId_alumno(rs.getInt("id_alumno"));
                a.setNombre(rs.getString("nombre"));
                a.setApellido(rs.getString("apellido"));
                a.setFecha_nac(rs.getDate("fecha_nac").toLocalDate());
                a.setLegajo(rs.getLong("legajo"));
                a.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar alumno");
            
        }
        return a;
    }
    
    public List<Alumno> listarAlumno(){
        List<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT * FROM alumno";
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
            JOptionPane.showMessageDialog(null, "Error al listar alumnos");
        }
        return alumnos;
    }
    
    public void actualizarAlumno(Alumno a){//el alumno lo recupero a traves de la vista
        //con una previa busqueda por legajo, que me recupere todos los datos
        String query = "UPDATE alumno SET nombre=?,apellido=?,fecha_nac=?,legajo=? WHERE id_alumno=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(5, a.getId_alumno());
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setDate(3, Date.valueOf(a.getFecha_nac()));
            ps.setLong(4, a.getLegajo());
            
            if(ps.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null, "El alumno se modifico correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "No se actualizo el alumno");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar alumno");
        }
    }
    
    public void borrarAlumno(int id){//desde la vista recupero el alumno, y de ahi obtener el id,
        
        
        
        String query = "UPDATE alumno set estado=false WHERE id_alumno=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            
            if(ps.executeUpdate() == 1){// el metodo executeUpdate devuelve un entero
                //si le mandas insert , retorna la cantidad de filas que agrego
                //si le mandas delete, retorna la cantidad de filas que borro
                //si le mandas un update, retorna la cantidad de filas que actualizo
                JOptionPane.showMessageDialog(null, "Borrado exitosamente");
            }else{
                JOptionPane.showMessageDialog(null,"El alumno que se desea borrar no existe");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar alumno");
        }
    }
    
    //----------- METODOS PARA VISTAS --------------------
    
    public void borrarAlumnoF(long legajo)throws SQLException {
        //version modificada para prevenir errores en la vista
        String query = "DELETE FROM alumno WHERE legajo=?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setLong(1, legajo);

        if(ps.executeUpdate() == 1){
            JOptionPane.showMessageDialog(null, "Borrado exitosamente");
        }else{
            JOptionPane.showMessageDialog(null,"El alumno que se desea borrar no existe");
        }
        ps.close();
    }
    
    public void borrarAlumnoL(long legajo){
        //sin uso por ahora
        String query = "UPDATE alumno SET estado=false WHERE legajo=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setLong(1, legajo);
            if(ps.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null, "Borrado exitosamente");
            }else{
                JOptionPane.showMessageDialog(null,"El alumno que se desea borrar no existe");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar alumno");
        }
    }
    
}
