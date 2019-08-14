/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import models.ImageString;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javazoom.jl.decoder.JavaLayerException;
import manejador.ListEntry;
import manejador.ListEntryCellRenderer;
import models.Personaje;
import models.Pokedex;

/**
 *
 * @author Mansilla
 */
public class Seleccion extends javax.swing.JFrame {
     Estadio estadio;
    Pokedex pokedex = new Pokedex();
    Pokedex alterna = new Pokedex();
    int trio =0;
    int random1 = 0;
    int random2 = 0;
    int random3 = 0;
    /**
     * Creates new form Combate
     * @param l
     */
    
    public Seleccion(){
        initComponents();
    }
    
    public Seleccion(Pokedex p) {
       
        this.initComponents();
        
        DefaultListModel dlm = new DefaultListModel();
        
        alterna = p;
        pokedex = p;
       
        for(Personaje k: pokedex.getTodos()){            
           Icon x = new ImageIcon(k.getRutaImagen()); 
           
           dlm.addElement(new ListEntry(k.getNombre(), scaleImage((ImageIcon) x,100,100), new JButton("x")));
        }
        
        jList1.setModel(dlm);
        jList1.setCellRenderer(new ListEntryCellRenderer());
        jList1.updateUI();
                    
         jList1.setSelectionModel(new DefaultListSelectionModel() {

             @Override
        public void setSelectionInterval(int index0, int index1) {
        if(super.isSelectedIndex(index0)) {
            super.removeSelectionInterval(index0, index1);
            trio--;
        }
        else {
            if(trio < 3 ){
                super.addSelectionInterval(index0, index1);
                trio++;
            }
            
        }
        }
    
        });
        
          random1 = (int) (Math.random() * pokedex.getTodos().size());
          random2 = (int) (Math.random() * pokedex.getTodos().size());
          random3 = (int) (Math.random() * pokedex.getTodos().size());
          while(true){
              if(random1 == random2){
                   random2 = (int) (Math.random() * pokedex.getTodos().size());
              }else{
                  break;
              }
             
          }
          
          while(true){
              if(random2 == random3 || random1==random3){
                   random2 = (int) (Math.random() * pokedex.getTodos().size());
              }else{
                  break;
              }
             
          }
                           
        jList1.getSelectionModel().addSelectionInterval(random1, random1);
        jList1.getSelectionModel().addSelectionInterval(random2, random2);
        jList1.getSelectionModel().addSelectionInterval(random3, random3);
        this.setVisible(true);
        
        trio =3;
        
    jList1.addMouseMotionListener(new MouseAdapter() {
    public void mouseMoved(MouseEvent me) {
    Point p = new Point(me.getX(),me.getY());
    int index = jList1.locationToIndex(p);

    Icon y = new ImageIcon(pokedex.getTodos().get(index).getRutaImagen());
        jFoto.setIcon(scaleImage((ImageIcon) y, 250, 250));
        
        
        jFicha.setText(
                "Nombre:"+ pokedex.getTodos().get(index).getNombre() + "\n" +
                "Vida:" +  pokedex.getTodos().get(index).getVida() + "\n" + 
                "Tipo:" +  pokedex.getTodos().get(index).getNivel() + "\n" + 
                "Principiante:" + pokedex.getTodos().get(index).getPrincipiante() +"\n" + 
                "Intermedio:" + pokedex.getTodos().get(index).getIntermedio() +"\n" + 
                "Master:" + pokedex.getTodos().get(index).getMaster()
        );
    }
    });
        
    }

    
       public static  ImageIcon scaleImage(ImageIcon icon, int w, int h)
    {
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();

        if(icon.getIconWidth() > w)
        {
          nw = w;
          nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }

        if(nh > h)
        {
          nh = h;
          nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }

        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_AREA_AVERAGING));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jFoto = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jFicha = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        }
    );
    jList1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        public void mouseMoved(java.awt.event.MouseEvent evt) {
            jList1MouseMoved(evt);
        }
    });
    jList1.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
        public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
            jList1MouseWheelMoved(evt);
        }
    });
    jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            jList1ValueChanged(evt);
        }
    });
    jScrollPane1.setViewportView(jList1);

    jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel1.setText("Informacion:");

    jFoto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

    jFicha.setEditable(false);
    jFicha.setColumns(20);
    jFicha.setRows(5);
    jScrollPane2.setViewportView(jFicha);

    jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jButton1.setText("Jugar");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(61, 61, 61)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(121, 121, 121)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(126, 126, 126)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(128, 128, 128))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2)
                        .addComponent(jFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(70, 70, 70))))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap(38, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(13, 13, 13))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(21, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        
    }//GEN-LAST:event_jList1ValueChanged

    private void jList1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseMoved
     
    }//GEN-LAST:event_jList1MouseMoved

    private void jList1MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jList1MouseWheelMoved
       
    }//GEN-LAST:event_jList1MouseWheelMoved

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(trio == 3){
           try {
               for(Personaje p: this.pokedex.getTodos()){
                   if(p.getUso()==1){
                       Personaje personaje = new Personaje();
                       personaje.setIntermedio(new Integer(p.getIntermedio()));
                       personaje.setMaster(new Integer(p.getMaster()));
                       personaje.setPrincipiante(new Integer(p.getPrincipiante()));
                       personaje.setVida(new Integer(p.getVida()));
                       personaje.setTipo(new Integer(p.getTipo()));
                       personaje.setNivel(new String(p.getNivel()));
                       personaje.setNombre(new String(p.getNombre()));
                       personaje.setRutaImagen(new String(p.getRutaImagen()));
                       personaje.setTiempo(new Integer(p.getTiempo()));
//                       personaje.setRutaSonido(new String(p.getRutaSonido()));
                       pokedex.getEnemigos().add(personaje);
                   }
               }
               
               for(int i: this.jList1.getSelectedIndices()){
                   Personaje personaje = new Personaje();
                   personaje.setIntermedio(new Integer(pokedex.getTodos().get(i).getIntermedio()));
                   personaje.setMaster(new Integer(pokedex.getTodos().get(i).getMaster()));
                   personaje.setPrincipiante(new Integer(pokedex.getTodos().get(i).getPrincipiante()));
                   personaje.setVida(new Integer(pokedex.getTodos().get(i).getVida()));
                   personaje.setTipo(new Integer(pokedex.getTodos().get(i).getTipo()));
                   personaje.setNivel(new String(pokedex.getTodos().get(i).getNivel()));
                   personaje.setNombre(new String(pokedex.getTodos().get(i).getNombre()));
                   personaje.setRutaImagen(new String(pokedex.getTodos().get(i).getRutaImagen()));
                   personaje.setTiempo(new Integer(pokedex.getTodos().get(i).getTiempo()));
                   try{
                       personaje.setRutaSonido(new String(pokedex.getTodos().get(i).getRutaSonido()));
                   }catch(NullPointerException j){
                       
                   }
                   
                   pokedex.getAliados().add(personaje);
               }
               
               estadio = new Estadio(this.pokedex);
               estadio.run();
               estadio.setVisible(true);
               
               System.gc();
               
               Pokedex temp = new Pokedex();
               temp.setTodos(pokedex.getTodos());
               pokedex = temp;
           } catch (JavaLayerException ex) {
               Logger.getLogger(Seleccion.class.getName()).log(Level.SEVERE, null, ex);
           }
        
       }else{
           JOptionPane.showMessageDialog(null, "Por favor selecciones 3 personajes");  
       }
    }//GEN-LAST:event_jButton1ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Seleccion().setVisible(true);
            System.out.println("sadas");
        });
    }
    
    
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JTextArea jFicha;
    private javax.swing.JLabel jFoto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables


}


