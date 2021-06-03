package universidadgrupo5.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import universidadgrupo5.modelo.Alumno;
import universidadgrupo5.modelo.Cursada;
import universidadgrupo5.modelo.Materia;
import universidadgrupo5.vistas.viewCargarNotas;
import universidadgrupo5.vistas.viewInscripcion;

/* @author @LXWeber Leandro Xavier Weber */
public class ctrlCursada implements ActionListener {

    private viewCargarNotas vcn;
    private viewInscripcion vi;
    private CursadaData cd;
    private List<Materia> listaMaterias = new ArrayList<>();
    private List<Cursada> listaCursadas = new ArrayList<>();
    private DefaultTableModel tablaCursada;
    private HashMap<Object, Integer> fuente = new HashMap<>();

    public ctrlCursada(viewInscripcion vi, CursadaData cd, List<Alumno> la) {
        this.vi = vi;
        this.cd = cd;
        tablaCursada = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        for (Alumno alumno : la) {
            if(alumno.isEstado()){
                vi.getjcbAlumnos().addItem(alumno);
            }
        }
        vi.getjcbAlumnos().addActionListener(this);
        vi.getJbInscribir().addActionListener(this);
        vi.getJbDesinscribir().addActionListener(this);
        vi.getjrbMateriasInscripto().addActionListener(this);
        vi.getjrbMateriasNoInscripto().addActionListener(this);
        vi.getJbSalir().addActionListener(this);
        fuente.put(vi.getJbInscribir(), 1);
        fuente.put(vi.getJbDesinscribir(), 2);
        fuente.put(vi.getjcbAlumnos(), 3);
        fuente.put(vi.getjrbMateriasInscripto(), 4);
        fuente.put(vi.getjrbMateriasNoInscripto(), 5);
        fuente.put(vi.getJbSalir(), 6);
        cabeceraInscripcion();
        Alumno alumno = (Alumno) vi.getjcbAlumnos().getSelectedItem();
        llenarListaCursadas(vi, alumno);
    }

    public ctrlCursada(viewCargarNotas vcn, CursadaData cd, List<Alumno> la) {
        this.vcn = vcn;
        this.cd = cd;
        tablaCursada = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return columna == 3;
            }
        };
        la.forEach((alumno) -> {
            if(alumno.isEstado()){
                vcn.getjcbAlumnos().addItem(alumno);
            }
        });
        vcn.getJbGuardar().addActionListener(this);
        vcn.getjcbAlumnos().addActionListener(this);
        vcn.getJbSalir().addActionListener(this);
        fuente.put(vcn.getJbGuardar(), 7);
        fuente.put(vcn.getjcbAlumnos(), 8);
        fuente.put(vcn.getJbSalir(),9);
        cabeceraCNotas();
        Alumno alumno = (Alumno) vcn.getjcbAlumnos().getSelectedItem();
        llenarListaCursadas(vcn, alumno);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Alumno alumno;
        if (vi != null) {
            alumno = (Alumno) vi.getjcbAlumnos().getSelectedItem();
        } else {
            alumno = (Alumno) vcn.getjcbAlumnos().getSelectedItem();
        }
        switch (fuente.get(ae.getSource())) {//viewInscripción:
            case 1: {//jbInscribir
                if (gsr(vi) != -1 && gsc(vi) != -1) {
                    Materia materia = new Materia();
                    materia.setId_materia((int) vfa(vi, 0));
                    Cursada cursada = new Cursada(alumno, materia, 0);
                    cd.inscribir(cursada);
                    tablaCursada.removeRow(gsr(vi));
                    vi.getJtListado().setModel(tablaCursada);
                }
                break;
            }
            case 2: {//jbDesinscribir
                if (gsr(vi) != -1 && gsc(vi) != -1) {
                    int i = (int) vfa(vi, 0);
                    cd.borrarCursadaDeUnaMateriaDeUnAlumno(alumno.getId_alumno(), i);
                    tablaCursada.removeRow(gsr(vi));
                    vi.getJtListado().setModel(tablaCursada);
                }
                break;
            }
            case 3: {//jcbAlumnos

            }
            case 4: {//jrbMateriasInscripto
                borrarLista();
                if (vi.getjrbMateriasInscripto().isSelected()) {
                    vi.getJbDesinscribir().setEnabled(true);
                    vi.getJbInscribir().setEnabled(false);
                    llenarListaCursadas(vi, alumno);
                    break;
                }
            }
            case 5: {//jrbMateriasNoInscripto
                borrarLista();
                vi.getJbInscribir().setEnabled(true);
                vi.getJbDesinscribir().setEnabled(false);
                llenarListaNoCursadas(alumno);
                break;
            }
            case 6: {//jbSalir
                vi.dispose();
                break;
            }

            //viewCargarNotas
            case 7: {//jbGuardar
                for (int i = 0; i < tablaCursada.getRowCount(); i++) {
                    Object aux = tablaCursada.getValueAt(i, 3);
                    cd.actualizarNotaCursada(
                        alumno.getId_alumno(),
                        (int) tablaCursada.getValueAt(i, 0),
                        Double.parseDouble(aux.toString())
                    );
                }
                JOptionPane.showMessageDialog(null, "Notas guardadas con exito.");
                break;
            }
            case 8: {//jcbAlumnos
                borrarLista();
                llenarListaCursadas(vcn, alumno);
                break;
            }
            case 9:{
                vcn.dispose();;
                break;
            }
        }
    }

    private void borrarLista() {
        for (int i = tablaCursada.getRowCount() - 1; i >= 0; i--) {
            tablaCursada.removeRow(i);
        }
    }

    private void llenarListaCursadas(viewInscripcion vi, Alumno a) {
        listaMaterias = cd.obtenerMateriasCursadas(a.getId_alumno());
        for (Materia m : listaMaterias) {
            if(m.getEstado()){
                tablaCursada.addRow(new Object[]{m.getId_materia(), m.getNombre_materia(), m.getAnio()});
            }
        }
        vi.getJtListado().setModel(tablaCursada);
    }

    private void llenarListaCursadas(viewCargarNotas vcb, Alumno a) {
        listaMaterias = cd.obtenerMateriasCursadas(a.getId_alumno());
        listaCursadas = cd.obtenerCursadasXAlumno(a.getId_alumno());
        for (int i = 0; i < listaMaterias.size(); i++) {
            if(listaMaterias.get(i).getEstado()){
            tablaCursada.addRow(new Object[]{
                listaMaterias.get(i).getId_materia(),
                listaMaterias.get(i).getNombre_materia(),
                listaMaterias.get(i).getAnio(),
                listaCursadas.get(i).getNota()});
            }
        }
        vcn.getJtListado().setModel(tablaCursada);
    }

    private void llenarListaNoCursadas(Alumno a) {
        listaMaterias = cd.obtenerMateriasCursadas(a.getId_alumno());
        for (Materia m : cd.obtenerMateriasNOCursadas(a.getId_alumno())) {
            if(m.getEstado()){
                tablaCursada.addRow(new Object[]{m.getId_materia(), m.getNombre_materia(), m.getAnio()});  
            }
        }
        vi.getJtListado().setModel(tablaCursada);
    }

    private void cabeceraInscripcion() {
        tablaCursada.addColumn("ID");
        tablaCursada.addColumn("Nombre");
        tablaCursada.addColumn("Año");
        vi.getJtListado().setModel(tablaCursada);
    }

    private void cabeceraCNotas() {
        tablaCursada.addColumn("ID");
        tablaCursada.addColumn("Nombre");
        tablaCursada.addColumn("Año");
        tablaCursada.addColumn("Nota");
        vcn.getJtListado().setModel(tablaCursada);
    }

    // get selected row
    private int gsr(viewInscripcion vi) {
        return vi.getJtListado().getSelectedRow();
    }

    private int gsr(viewCargarNotas vcn) {
        return vcn.getJtListado().getSelectedRow();
    }

    // get selected column
    private int gsc(viewInscripcion vi) {
        return vi.getJtListado().getSelectedColumn();
    }

    private int gsc(viewCargarNotas vcn) {
        return vcn.getJtListado().getSelectedColumn();
    }

    // valor fila actual
    private Object vfa(viewInscripcion vi, int columna) {
        return vi.getJtListado().getValueAt(gsr(vi), columna);
    }

    private Object vfa(viewCargarNotas vcn, int columna) {
        return vcn.getJtListado().getValueAt(gsr(vcn), columna);
    }
}
