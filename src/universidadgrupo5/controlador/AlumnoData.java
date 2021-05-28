package universidadgrupo5.controlador;

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

    private Connection conexion = null;

    public AlumnoData(Conexion conexion) {
        this.conexion = conexion.getConexion();
    }

    public void guardarAlumno(Alumno a) {
        String query = "INSERT INTO alumno( nombre, apellido, fecha_nac, legajo, estado) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setDate(3, Date.valueOf(a.getFecha_nac()));
            ps.setLong(4, a.getLegajo());
            ps.setBoolean(5, a.isEstado());
            if (ps.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "Alumno guardado correctamente");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            a.setId_alumno(rs.getInt(1));
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar alumno. Asegurese de que el legajo no este repetido.");
        }
    }

    public Alumno buscarAlumno(long legajo) {
        Alumno a = new Alumno();
        String query = "SELECT id_alumno, nombre, apellido, fecha_nac, legajo, estado FROM alumno WHERE legajo=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setLong(1, legajo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

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

    public List<Alumno> listarAlumno() {
        List<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT * FROM alumno";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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

    public void actualizarAlumno(Alumno a) {
        String query = "UPDATE alumno SET nombre=?,apellido=?,fecha_nac=?,legajo=? WHERE id_alumno=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(5, a.getId_alumno());
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setDate(3, Date.valueOf(a.getFecha_nac()));
            ps.setLong(4, a.getLegajo());

            if (ps.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "El alumno se modifico correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se actualizo el alumno");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar alumno");
        }
    }

    public void borrarAlumnoFisico(long legajo) {
        String query = "DELETE FROM alumno WHERE legajo=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setLong(1, legajo);

            if (ps.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "Borrado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "El alumno que se desea borrar no existe");
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar alumno. Asegúrese de que el alumno no este inscripto a ninguna materia antes de intentar eliminarlo permanentemente.");
        }
    }

    public void borrarAlumnoLogico(long legajo) {
        String query = "UPDATE alumno SET estado=false WHERE legajo=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setLong(1, legajo);
            if (ps.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "Alumno deshabilitado.");
            } else {
                JOptionPane.showMessageDialog(null, "No existe un alumno con ese legajo.");
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar estado del alumno.");
        }
    }
    //el borrado logico y esta funcion de restaurar podrian unirse en un solo metodo cambiarEstado o algo asi

    public void restaurarAlumno(long legajo) {
        String query = "UPDATE alumno SET estado=true WHERE legajo=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setLong(1, legajo);
            if (ps.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "Alumno habilitado.");
            } else {
                JOptionPane.showMessageDialog(null, "No existe un alumno con ese legajo.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar estado del alumno.");
        }
    }

    //----------- METODOS PARA VISTAS --------------------
    public void borrarAlumnoF(long legajo) throws SQLException {
        //version modificada para prevenir errores en la vista
        String query = "DELETE FROM alumno WHERE legajo=?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setLong(1, legajo);

        if (ps.executeUpdate() == 1) {
            JOptionPane.showMessageDialog(null, "Borrado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "El alumno que se desea borrar no existe");
        }
        ps.close();
    }

}
