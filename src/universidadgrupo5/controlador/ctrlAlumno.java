    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo5.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import universidadgrupo5.modelo.Alumno;
import universidadgrupo5.vistas.viewListarAlumnos;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author Laucha
 */
public class ctrlAlumno implements ActionListener, TableModelListener, PropertyChangeListener {
    private viewListarAlumnos vla;
    private AlumnoData ad;
    private List<Alumno> lista = new ArrayList<>();
    private DefaultTableModel model;
        
    public ctrlAlumno(viewListarAlumnos vla, AlumnoData ad){
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return !(column == 0 || column == 3 || column==4);
            }
        };
        model.addTableModelListener(this);
        this.vla=vla;
        this.ad=ad;
        lista = ad.listarAlumno();
        vla.getJbBorrar().addActionListener(this);
        vla.getJbSalir().addActionListener(this);
        vla.getJdcFecha().addPropertyChangeListener(this);
        cabecera();
        llenarLista();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vla.getJbBorrar()){
            try {
                Long id = (Long)vla.getJtListado().getValueAt(vla.getJtListado().getSelectedRow(), 0);
                ad.borrarAlumnoF(id);
                model.removeRow(vla.getJtListado().getSelectedRow());
                vla.getJtListado().setModel(model);
            } catch (SQLException sqle){
                JOptionPane.showMessageDialog(vla, "Error al borrar alumno. Asegúrese de que el alumno no este inscripto a ninguna materia antes de intentar eliminarlo permanentemente.");
            }
        }
        if (ae.getSource() == vla.getJbSalir()){
            vla.dispose();
        }
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (!(pce.getNewValue() instanceof JPanel)){
            java.util.Date aux = (java.util.Date) pce.getNewValue();
            if(aux!=null){
                
                String aux2 = aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
                if(vla.getJtListado().getSelectedRow()!=-1){
                    vla.getJtListado().setValueAt(aux2, vla.getJtListado().getSelectedRow(), 3);
                }
                 
            }
            
        }
        
    }
    
    private void cabecera(){
        ArrayList<Object> c = new ArrayList<>();
        c.add("Legajo");
        c.add("Nombre");
        c.add("Apellido");
        c.add("Fecha de Nacimiento");
        c.add("Estado");
        for (Object a:c){
            model.addColumn(a);
        }
        vla.getJtListado().setModel(model);
    }
    
    private void llenarLista(){
        for (Alumno a:lista){
            model.addRow(new Object[]{a.getLegajo(),a.getNombre(),a.getApellido(),a.getFecha_nac(),a.isEstado()});
        }
        vla.getJtListado().setModel(model);
    }

    @Override
    public void tableChanged(TableModelEvent tme) {
        if (vla.getJtListado().getSelectedRow()!=-1 && vla.getJtListado().getSelectedColumn()!=-1 ){
            Object edit = vla.getJtListado().getValueAt(vla.getJtListado().getSelectedRow(),vla.getJtListado().getSelectedColumn());
            Long id = (Long)vla.getJtListado().getValueAt(vla.getJtListado().getSelectedRow(),0);
            for (Alumno a:lista){
                if (a.getLegajo()==id){
                    a.setNombre((String) vla.getJtListado().getValueAt(vla.getJtListado().getSelectedRow(), 1));
                    a.setApellido((String) vla.getJtListado().getValueAt(vla.getJtListado().getSelectedRow(), 2));
                    Object fecha = vla.getJtListado().getValueAt(vla.getJtListado().getSelectedRow(), 3);
                    a.setFecha_nac(LocalDate.parse(fecha.toString()));
                    a.setEstado((Boolean) vla.getJtListado().getValueAt(vla.getJtListado().getSelectedRow(), 4));
                    ad.actualizarAlumno(a);
                }
            }
        }
    }   
}