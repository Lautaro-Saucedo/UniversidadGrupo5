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
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import universidadgrupo5.vistas.viewAgregarAlumno;

/**
 *
 * @author Laucha
 */
public class ctrlAlumno implements ActionListener, TableModelListener, PropertyChangeListener {

    private viewListarAlumnos vla;
    private viewAgregarAlumno vaa;
    private AlumnoData ad;
    private List<Alumno> lista = new ArrayList<>();
    private DefaultTableModel model;
    private HashMap<Object, Integer> fuente = new HashMap<>();

    public ctrlAlumno(viewListarAlumnos vla, AlumnoData ad) {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return !(column == 0 || column == 3 || column == 4);
            }
        };
        model.addTableModelListener(this);
        this.vla = vla;
        this.ad = ad;
        lista = ad.listarAlumno();
        vla.getJbBorrar().addActionListener(this);
        vla.getJbEstado().addActionListener(this);
        vla.getJdcFecha().addPropertyChangeListener(this);
        fuente.put(vla.getJbBorrar(), 1);
        fuente.put(vla.getJbEstado(), 2);
        fuente.put(vla.getJdcFecha(), 3);
        cabecera();
        llenarLista();
    }

    public ctrlAlumno(viewAgregarAlumno vaa, AlumnoData ad) {
        this.vaa = vaa;
        this.ad = ad;
        vaa.getJbAgregar().addActionListener(this);
        vaa.getJbLimpiar().addActionListener(this);
        vaa.getJdcFecha().addPropertyChangeListener(this);
        fuente.put(vaa.getJbAgregar(), 4);
        fuente.put(vaa.getJbLimpiar(), 5);
        fuente.put(vaa.getJdcFecha(), 6);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (fuente.get(ae.getSource())) {
            // ---------- botones de viewListarAlumnos ----------------
            case 1: {
                try {
                    if (gsr() != -1 && gsc() != -1) {
                        Long id = (Long) vfa(0);
                        ad.borrarAlumnoF(id);
                        model.removeRow(gsr());
                        vla.getJtListado().setModel(model);
                    }
                } catch (SQLException sqle) {
                    JOptionPane.showMessageDialog(vla, "Error al borrar alumno. Asegúrese de que el alumno no este inscripto a ninguna materia antes de intentar eliminarlo permanentemente.");
                }
                break;
            }
            case 2: {
                try {
                    if ((Boolean) vfa(4)) {
                        ad.borrarAlumnoLogico((Long) vfa(0));
                        vla.getJtListado().setValueAt(false, gsr(), 4);
                    } else {
                        ad.restaurarAlumno((Long) vfa(0));
                        vla.getJtListado().setValueAt(true, gsr(), 4);
                    }
                } catch (Exception e) {

                }
                break;
            }
            // ------------ botones de viewAgregarAlumno -------------
            case 4: {
                try {
                    if (vaa.getJtfApellido().getText().equals("") || vaa.getJtfNombre().getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Error. Nombre/Apellido no puede estar en blanco.");
                    } else {
                        Alumno a = new Alumno();
                        a.setLegajo(Long.parseLong(vaa.getJtfLegajo().getText()));
                        a.setNombre(vaa.getJtfNombre().getText());
                        a.setApellido(vaa.getJtfApellido().getText());
                        a.setFecha_nac(LocalDate.parse(vaa.getJtfFecha().getText()));
                        a.setEstado(vaa.getJcbEstado().isSelected());
                        ad.guardarAlumno(a);
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Error. Legajo solo puede contener numeros.");
                } catch (DateTimeParseException dpe) {
                    JOptionPane.showMessageDialog(null, "Error. Fecha de nacimiento no puede estar en blanco");
                }
                break;
            }
            case 5: {
                vaa.getJtfLegajo().setText("");
                vaa.getJtfNombre().setText("");
                vaa.getJtfApellido().setText("");
                vaa.getJtfFecha().setText("");
                vaa.getJcbEstado().setSelected(false);
                break;
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (!(pce.getNewValue() instanceof JPanel)) {
            switch (fuente.get(pce.getSource())) {
                case 3: {
                    java.util.Date aux = (java.util.Date) pce.getNewValue();
                    if (aux != null) {
                        String aux2 = aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
                        if (gsr() != -1) {
                            vla.getJtListado().setValueAt(aux2, gsr(), 3);
                        }
                    }
                    break;
                }
                case 6: {
                    java.util.Date aux = (java.util.Date) pce.getNewValue();
                    if (aux != null) {
                        String aux2 = aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
                        vaa.getJtfFecha().setText(aux2);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void tableChanged(TableModelEvent tme) {
        if (gsr() != -1 && gsc() != -1) {
            if (!(tme.getColumn() == 4)) {
                Object edit = vfa(gsc());
                Long id = (Long) vfa(0);
                for (Alumno a : lista) {
                    if (a.getLegajo() == id) {
                        a.setNombre((String) vfa(1));
                        a.setApellido((String) vfa(2));
                        Object fecha = vfa(3);
                        a.setFecha_nac(LocalDate.parse(fecha.toString()));
                        ad.actualizarAlumno(a);
                    }
                }
            }
        }
    }

    //----------------------------------------------------
    //valor fila actual
    private Object vfa(int columna) {
        return vla.getJtListado().getValueAt(gsr(), columna);
    }

    //get selected row
    private int gsr() {
        return vla.getJtListado().getSelectedRow();
    }

    //get selected column
    private int gsc() {
        return vla.getJtListado().getSelectedColumn();
    }

    private void cabecera() {
        model.addColumn("Legajo");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Fecha de Nacimiento");
        model.addColumn("Estado");
        vla.getJtListado().setModel(model);
    }

    private void llenarLista() {
        for (Alumno a : lista) {
            model.addRow(new Object[]{a.getLegajo(), a.getNombre(), a.getApellido(), a.getFecha_nac(), a.isEstado()});
        }
        vla.getJtListado().setModel(model);
    }
}
