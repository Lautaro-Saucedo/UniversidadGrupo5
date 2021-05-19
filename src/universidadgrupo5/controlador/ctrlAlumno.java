/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo5.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import universidadgrupo5.modelo.Alumno;
import universidadgrupo5.vistas.viewListarAlumnos;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author Laucha
 */
public class ctrlAlumno implements ActionListener, ListSelectionListener, TableModelListener {
    private viewListarAlumnos vla;
    private AlumnoData ad;
    private List<Alumno> lista = new ArrayList<>();
    private DefaultTableModel model;
    private Object original=null;
        
    public ctrlAlumno(viewListarAlumnos vla, AlumnoData ad){
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return !(column == 0 || column == 4);
            }
        };
        model.addTableModelListener(this);
        this.vla=vla;
        this.ad=ad;
        lista = ad.listarAlumno();
        vla.getJbGuardar().addActionListener(this);
        vla.getJbBorrar().addActionListener(this);
        vla.getJbSalir().addActionListener(this);
        cabecera();
        llenarLista();
        vla.getJtListado().getSelectionModel().addListSelectionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vla.getJbBorrar()){
            Long id = (Long)vla.getJtListado().getValueAt(vla.getJtListado().getSelectedRow(), 0);
            ad.borrarAlumnoF(id);
            model.removeRow(vla.getJtListado().getSelectedRow());
            vla.getJtListado().setModel(model);
        }
        if(ae.getSource() == vla.getJbGuardar()){
            System.out.println("click en guardar");
        }
        if (ae.getSource() == vla.getJbSalir()){
            vla.dispose();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if (lse.getValueIsAdjusting()){
            original = vla.getJtListado().getValueAt(vla.getJtListado().getSelectedRow(),vla.getJtListado().getSelectedColumn());
            System.out.println(original);
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
            String edit = (String)vla.getJtListado().getValueAt(vla.getJtListado().getSelectedRow(),vla.getJtListado().getSelectedColumn());
            String nomcol = (String) vla.getJtListado().getColumnName(vla.getJtListado().getSelectedColumn());
            Long id = (Long)vla.getJtListado().getValueAt(vla.getJtListado().getSelectedRow(),0);
            for (Alumno a:lista){
                if (a.getLegajo()==id){
                    if (edit.equals(original)){
                        
                    } else {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        a.setNombre((String)vla.getJtListado().getValueAt(vla.getJtListado().getSelectedRow(),1));
                        a.setApellido((String)vla.getJtListado().getValueAt(vla.getJtListado().getSelectedRow(),2));
                        String fecha = (String)vla.getJtListado().getValueAt(vla.getJtListado().getSelectedRow(),3);
                        a.setFecha_nac((LocalDate.parse(fecha,dtf)));
                        a.setEstado((Boolean)vla.getJtListado().getValueAt(vla.getJtListado().getSelectedRow(),4));
                        ad.actualizarAlumno(a);
                    }
                }
            }
            
        }
    }

        
}