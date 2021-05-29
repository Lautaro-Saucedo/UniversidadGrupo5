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
import universidadgrupo5.modelo.Cursada;
import universidadgrupo5.vistas.viewCargarNotas;
import universidadgrupo5.vistas.viewInscripcion;

/* @author @LXWeber Leandro Xavier Weber */

public class ctrlCursada implements ActionListener, TableModelListener, PropertyChangeListener{
    
    private viewCargarNotas vcg;
    private viewInscripcion vi;
   // private cursadaData cd; esto me da error por alguna razón, luego vemo.
    private List<Cursada> listaCursada = new ArrayList<>();
    private DefaultTableModel tablaCursada;

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
