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
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
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
    private HashMap<Object,Integer> enumFuente = new HashMap<>();

    public ctrlMateria(viewListarMaterias vlm, MateriaData md) {//este es mi comentario
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
        vlm.getJbSalir().addActionListener(this);
        vlm.getJbCambiarEstado().addActionListener(this);
        enumFuente.put(vlm.getJbBorrar(), 1);
        enumFuente.put(vlm.getJbSalir(), 2);
        enumFuente.put(vlm.getJbCambiarEstado(), 3);
        cabeceraTabla();
        contenidoTabla();        
    }
    private Object vfa(int columna) {
        return vlm.getJtListado().getValueAt(gsr(), columna);
    }

    //get selected row
    private int gsr() {
        return vlm.getJtListado().getSelectedRow();
    }

    //get selected column
    private int gsc() {
        return vlm.getJtListado().getSelectedColumn();
    }
    
    public ctrlMateria(viewAgregarMateria vam, MateriaData md){
        this.vam = vam;
        this.md = md;
        listaMaterias = md.listarMaterias();
        vam.getJbAgregar().addActionListener(this);
        vam.getJbLimpiar().addActionListener(this);
        vam.getJbSalir().addActionListener(this);
        enumFuente.put(vam.getJbAgregar(),4);
        enumFuente.put(vam.getJbLimpiar(),5);
        enumFuente.put(vam.getJbSalir(), 6);
        
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        //un switch para ver que boton desencadena el evento
        switch(enumFuente.get(ae.getSource())){//le paso el objeto del evento como clave a mi map
            // ---------------- botones de viewListarMaterias ---------------------
            case 1:{//boton borrar
                //no le puse un try catch xq ya esta en el metodo de materia data
                if (gsr() != -1 && gsc() != -1 ){
                    int id = (int)vfa(0);//recupero el id de la fila seleccionada
                    md.borrarMateriaF(id);
                    tablaMaterias.removeRow(gsr());
                    vlm.getJtListado().setModel(tablaMaterias);
                    break;
                }else{
                    JOptionPane.showMessageDialog(vlm, "Debe seleccionar una fila");
                    break;
                }
                
                              
            }
            case 2:{//boton salir
                vlm.dispose();
                break;
            }
            case 3:{//boton cambiar estado
                try {
                    if ((Boolean)vfa(3)){//recupero el valor de la columna tres, en la fila actual
                        md.borrarMateriaL((int)vfa(0));
                        tablaMaterias.setValueAt(false, gsr(), 3);
                        vlm.getJtListado().setModel(tablaMaterias);
                    } else {
                        md.restaurarMateria((int)vfa(0));
                        tablaMaterias.setValueAt(true, gsr(), 3);
                        vlm.getJtListado().setModel(tablaMaterias);
                    }
                } catch (Exception e){
                    System.out.println("Ha ocurrido una excepcion");
                }
                break;
            }
            // --------------- botones de viewAgregarMateria --------------------
            case 4:{//boton agregar
                try{
                String nombre_materia = vam.getJtNombre().getText();
                int agno = Integer.parseInt(vam.getJtAgno().getText());
                boolean estado = vam.getJcbEstado().isSelected();
                    if(!nombre_materia.isEmpty()){
                        Materia nuevaMateria = new Materia(nombre_materia,agno,estado);
                        md.guardarMateria(nuevaMateria);//materia data se va a encargar de setearle el id, me faltaria recuperarlo para mostrarlo en el jtfield correspondiente
                        vam.getJtCodigo().setText(nuevaMateria.getId_materia()+"");//muestro el id
                    }else{
                        JOptionPane.showMessageDialog(vam, "Falta el nombre de la materia");
                    } 
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(vam, "El formato del año de la carrera debe ser numerico");//faltaria agregarle tambien el rango, tal vez de 1-5 o 1-3 dependiendo la carrera
                }
                break;
            }
            case 5:{//boton limpiar
                vam.getJtCodigo().setText("");
                vam.getJtNombre().setText("");
                vam.getJtAgno().setText("");
                break;
            }
            case 6:{//boton salir
                vam.dispose();
            }
            
        }           
    }

    @Override
    public void tableChanged(TableModelEvent tme) {
        if (vlm.getJtListado().getSelectedRow()!=-1 && vlm.getJtListado().getSelectedColumn()!=-1 ){
            Object edit = vlm.getJtListado().getValueAt(gsr(),gsc());
            int id = (int)vfa(0);
            if(tme.getColumn() != 3){//cuando modifique solo la columna estado ,esto va a ser falso
                for (Materia m:listaMaterias){
                    if (m.getId_materia()==id){

                        try{
                            
                            Object agno = vfa(2);
                                                   
                            m.setAnio(Integer.parseInt(agno.toString()));
                            m.setNombre_materia((String) vfa(1));
                            md.actualizarMateria(m);
                        }catch(NumberFormatException nfe){
                            JOptionPane.showMessageDialog(vlm, "El año debe ser un numero");
                        }


                    }
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
