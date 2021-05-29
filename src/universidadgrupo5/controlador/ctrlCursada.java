package universidadgrupo5.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import universidadgrupo5.modelo.Alumno;
import universidadgrupo5.modelo.Cursada;
import universidadgrupo5.modelo.Materia;
import universidadgrupo5.vistas.viewCargarNotas;
import universidadgrupo5.vistas.viewInscripcion;

/* @author @LXWeber Leandro Xavier Weber */

public class ctrlCursada implements ActionListener, TableModelListener, PropertyChangeListener{
    
    private viewCargarNotas vcg;
    private viewInscripcion vi;
    private CursadaData cd;
    private List<Alumno> listaAlumnos = new ArrayList<>();
    private List<Materia> listaMaterias = new ArrayList<>();
    private List<Cursada> listaCursada = new ArrayList<>();
    private DefaultTableModel tablaCursada;

    public ctrlCursada(viewInscripcion vi, CursadaData cd) {
        this.vi=vi;
        this.cd=cd;
        for(Alumno alumno: listaAlumnos){
            vi.getjcbAlumnos().addItem(alumno);
        }
        tablaCursada.addTableModelListener(this);
        vi.getjcbAlumnos().addActionListener(this);
        vi.getJbInscribir().addActionListener(this);
        vi.getJbDesinscribir().addActionListener(this);
        vi.getjrbMateriasInscripto().addActionListener(this);
    }

    public ctrlCursada(viewCargarNotas vcg, CursadaData cd) {
        this.vcg=vcg;
        this.cd=cd;
        
        for(Alumno alumno: listaAlumnos){
            vcg.getjcbAlumnos().addItem(alumno);
        }
        tablaCursada.addTableModelListener(this);
        vcg.getJbGuardar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tableChanged(TableModelEvent tme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
