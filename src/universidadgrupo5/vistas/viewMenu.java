/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo5.vistas;

import universidadgrupo5.controlador.*;
import universidadgrupo5.modelo.*;

/**
 *
 * @author Laucha
 */
public class viewMenu extends javax.swing.JFrame {
    private Conexion con = new Conexion();
    
    private AlumnoData ad = new AlumnoData(con);
    private MateriaData md= new MateriaData(con);
    //laucha: aca se agregarian las 2 data restantes

    /**
     * Creates new form viewMenu
     */
    public viewMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiAgregarMateria = new javax.swing.JMenuItem();
        jmiListarMaterias = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiAgregarAlumno = new javax.swing.JMenuItem();
        jmiListarAlumnos = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jmiInscripcion = new javax.swing.JMenuItem();
        jmiCargarNotas = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jmiSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        jMenu1.setText("Materias");

        jmiAgregarMateria.setText("Agregar Materia");
        jMenu1.add(jmiAgregarMateria);

        jmiListarMaterias.setText("Listar Materias");
        jmiListarMaterias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiListarMateriasActionPerformed(evt);
            }
        });
        jMenu1.add(jmiListarMaterias);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Alumnos");

        jmiAgregarAlumno.setText("Agregar Alumno");
        jMenu2.add(jmiAgregarAlumno);

        jmiListarAlumnos.setText("Listar Alumnos");
        jmiListarAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiListarAlumnosActionPerformed(evt);
            }
        });
        jMenu2.add(jmiListarAlumnos);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Inscripcion");

        jmiInscripcion.setText("Formulario de Inscripcion");
        jMenu3.add(jmiInscripcion);

        jmiCargarNotas.setText("Cargar Notas");
        jMenu3.add(jmiCargarNotas);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Salir");

        jmiSalir.setText("Salir");
        jMenu4.add(jmiSalir);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiListarAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiListarAlumnosActionPerformed
        escritorio.removeAll();
        escritorio.repaint();
        viewListarAlumnos vla = new viewListarAlumnos();
        //creo que esta es la forma en la que se trabaja con los controles
        ctrlAlumno ca = new ctrlAlumno(vla,ad);
        // se le pasa la vista a un controlador con los listeners apropiados, y toda la magica
        // ocurre ahi adentro, tengo que preguntarle al profe
        escritorio.add(vla);
        vla.setVisible(true);
    }//GEN-LAST:event_jmiListarAlumnosActionPerformed

    private void jmiListarMateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiListarMateriasActionPerformed
        escritorio.removeAll();
        escritorio.repaint();
        viewListarMaterias vlm = new viewListarMaterias();
        ctrlMateria cm = new ctrlMateria(vlm,md);
        escritorio.add(vlm);
        vlm.setVisible(true);
    }//GEN-LAST:event_jmiListarMateriasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(viewMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jmiAgregarAlumno;
    private javax.swing.JMenuItem jmiAgregarMateria;
    private javax.swing.JMenuItem jmiCargarNotas;
    private javax.swing.JMenuItem jmiInscripcion;
    private javax.swing.JMenuItem jmiListarAlumnos;
    private javax.swing.JMenuItem jmiListarMaterias;
    private javax.swing.JMenuItem jmiSalir;
    // End of variables declaration//GEN-END:variables
}
