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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import universidadgrupo5.modelo.Materia;
import universidadgrupo5.vistas.viewAgregarMateria;
import universidadgrupo5.vistas.viewListarMaterias;

/**
 *
 * @author br1st
 */
public class ctrlMateria implements ActionListener, TableModelListener, PropertyChangeListener{
    private viewListarMaterias vlm;
    private viewAgregarMateria vam;
    private MateriaData md;
    private List<Materia> listaMaterias = new ArrayList<>();
    private DefaultTableModel tablaMaterias;

    public ctrlMateria(viewListarMaterias vlm, MateriaData md) {
        this.vlm = vlm;
        this.md = md;
        tablaMaterias = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return !(columna==3 || columna==0);
            }
            
        };
        tablaMaterias.addTableModelListener(this);
        listaMaterias = md.listarMaterias(); //ver que pasa cuando la lista que retorna esta vacia
        vlm.getJbBorrar().addActionListener(this);
        vlm.getJbGuardar().addActionListener(this);
        cabeceraTabla();
        contenidoTabla();
    }
    
    public ctrlMateria(viewAgregarMateria vam, MateriaData md){
        this.vam = vam;
        this.md = md;
        listaMaterias = md.listarMaterias();
        vam.getJbAgregar().addActionListener(this);
        vam.getJbLimpiar().addActionListener(this);
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vlm.getJbBorrar()){           
            int id = (int)vlm.getJtListado().getValueAt(vlm.getJtListado().getSelectedRow(), 0);
            md.borrarMateriaF(id);
            tablaMaterias.removeRow(vlm.getJtListado().getSelectedRow());
            vlm.getJtListado().setModel(tablaMaterias);         
        }
        
        if (ae.getSource() == vam.getJbAgregar()){
            try{
                String nombre_materia = vam.getJtNombre().getText();
                int agno = Integer.parseInt(vam.getJtAgno().getText());//me falta el try aca
                boolean estado = vam.getJcbEstado().isSelected();
                if(!nombre_materia.isEmpty()){
                    Materia nuevaMateria = new Materia(nombre_materia,agno,estado);
                    md.guardarMateria(nuevaMateria);//materia data se va a encargar de setearle el id, me faltaria recuperarlo para mostrarlo en el jtfield correspondiente
                    vam.getJtCodigo().setText(nuevaMateria.getId_materia()+"");//muestro el id
                    listaMaterias.add(nuevaMateria);//esta parte no se si es necesaria
                }else{
                    JOptionPane.showMessageDialog(vam, "Falta el nombre de la materia");
                } 
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(vam, "El formato del año de la carrera debe ser numerico");//faltaria agregarle tambien el rango, tal vez de 1-5 o 1-3 dependiendo la carrera
            }           
        }
        
        if(ae.getSource() == vam.getJbLimpiar()){
            vam.getJtCodigo().setText("");
            vam.getJtNombre().setText("");
            vam.getJtAgno().setText("");
        }
        
    }

    @Override
    public void tableChanged(TableModelEvent tme) {
        if (vlm.getJtListado().getSelectedRow()!=-1 && vlm.getJtListado().getSelectedColumn()!=-1 ){
            Object edit = vlm.getJtListado().getValueAt(vlm.getJtListado().getSelectedRow(),vlm.getJtListado().getSelectedColumn());
            int id = (int)vlm.getJtListado().getValueAt(vlm.getJtListado().getSelectedRow(),0);
            for (Materia m:listaMaterias){
                if (m.getId_materia()==id){
                    m.setNombre_materia((String) vlm.getJtListado().getValueAt(vlm.getJtListado().getSelectedRow(), 1));
                    m.setAnio(Integer.parseInt((String)vlm.getJtListado().getValueAt(vlm.getJtListado().getSelectedRow(), 2) ));
                    m.setEstado((Boolean) vlm.getJtListado().getValueAt(vlm.getJtListado().getSelectedRow(), 3));
                    md.actualizarMateria(m);
                    
                }
            }
        }
    }

    
    
    private void cabeceraTabla(){
        ArrayList<Object> o = new ArrayList<>();
        o.add("Id_Materia");
        o.add("Nombre de la Materia");
        o.add("Año");
        o.add("Estado");
        for(Object miobjeto:o){
            tablaMaterias.addColumn(miobjeto);
        }
        vlm.getJtListado().setModel(tablaMaterias);
    }
    
    private void contenidoTabla(){
        for (Materia m:listaMaterias){
            tablaMaterias.addRow(new Object[]{m.getId_materia(),m.getNombre_materia(),m.getAnio(),m.getEstado()});
        }
        vlm.getJtListado().setModel(tablaMaterias);
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


   
    
    
}
